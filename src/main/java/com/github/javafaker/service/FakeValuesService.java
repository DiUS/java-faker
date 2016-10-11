package com.github.javafaker.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.mifmif.common.regex.Generex;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeValuesService {
    private final Logger log = Logger.getLogger("faker");
    
    private final List<Map<String, Object>> fakeValuesMaps;
    
    private final RandomService randomService;

    /**
     * <p>
     *     Resolves YAML file using the most specific path first based on language and country code.
     *      'en_US' would resolve in the following order:
     *      <ol>
     *          <li>/en-US.yml</li>
     *          <li>/en.yml</li>
     *      </ol>
     *      The search is case-insensitive, so the following will all resolve correctly.  Also, either a hyphen or
     *      an underscore can be used when constructing a {@link Locale} instance.  This is legacy behavior and not
     *      condoned, but it will work.
     *      <ul>
     *          <li>EN_US</li>
     *          <li>En-Us</li>
     *          <li>eN_uS</li>
     *      </ul>
     * </p>
     * @param locale
     * @param randomService
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FakeValuesService(Locale locale, RandomService randomService) {
        if (locale == null) {
            throw new IllegalArgumentException("locale is required");
        }
        this.randomService = randomService;
        locale = normalizeLocale(locale);

        final List<Locale> locales = localeChain(locale);
        final List<Map<String,Object>> all = new ArrayList(locales.size());
        for (int i=0;i< locales.size();i++) {
            final Locale l = locales.get(i);
            
            final StringBuilder filename = new StringBuilder(l.getLanguage());
            if (!"".equals(l.getCountry())) {
                filename.append("-").append(l.getCountry());
            }
            
            final InputStream stream = findStream(filename.toString());
            if (stream != null) {
                all.add(fakerFromStream(stream, filename.toString()));
            }
        }

        if (all.size() == 1 && !locale.equals(Locale.ENGLISH)) {
            // if we have only successfully loaded ENGLISH and the requested locale
            // wasn't english that means we were unable to load the requested locale
            // in that case we vomit.
            // If someone requests FRANCE ("fr","FR") and we can't load fr_FR but we
            // load "fr", then that's ok. we picked up a variant. only if we ONLY pick up
            // the default do we throw that exception.
            throw new LocaleDoesNotExistException(locale.toString() + " does not exist");
        }

        this.fakeValuesMaps = Collections.unmodifiableList(all);
    }

    /**
     * @return the embedded faker: clause from the loaded Yml by the localeName, so .yml > en-us: > faker: 
     */
    protected Map fakerFromStream(InputStream stream, String localeName) {
        final Map valuesMap = new Yaml().loadAs(stream, Map.class);
        final Map localeBased = (Map) valuesMap.get(localeName.toString());
        return (Map) localeBased.get("faker");
    }

    /**
     * Convert the specified locale into a chain of locales used for message resolution. For example:
     * 
     * {@link Locale#FRANCE} (fr_FR) -> [ fr_FR, fr, en ]
     * 
     * @return a list of {@link Locale} instances
     */
    protected List<Locale> localeChain(Locale from) {
        if (Locale.ENGLISH.equals(from)) {
            return Collections.singletonList(Locale.ENGLISH);
        }

        final Locale normalized = normalizeLocale(from);

        final List<Locale> chain = new ArrayList(3);
        chain.add(normalized);
        if (!"".equals(normalized.getCountry()) && !Locale.ENGLISH.getLanguage().equals(normalized.getLanguage())) {
            chain.add(new Locale(normalized.getLanguage()));
        }
        chain.add(Locale.ENGLISH); // default
        return chain;
    }
   
    /**
     * @return a proper {@link Locale} instance with language and country code set regardless of how
     *         it was instantiated.  new Locale("pt-br") will be normalized to a locale constructed
     *         with new Locale("pt","BR").
     */
    private Locale normalizeLocale(Locale locale) {
        final String[] parts = locale.toString().split("[-\\_]");
        
        if (parts.length == 1) {
            return new Locale(parts[0]);
        } else {
            return new Locale(parts[0],parts[1]);
        }
    }

    private InputStream findStream(String filename) {
        String filenameWithExtension =  "/" + filename + ".yml";
        InputStream streamOnClass = getClass().getResourceAsStream(filenameWithExtension);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        return getClass().getClassLoader().getResourceAsStream(filenameWithExtension);
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    public Object fetch(String key) {
        List valuesArray = (List) fetchObject(key);
        return valuesArray == null ? null : valuesArray.get(randomService.nextInt(valuesArray.size()));
    }

    /**
     * Same as {@link #fetch(String)} except this casts the result into a String.
     *
     * @param key
     * @return
     */
    public String fetchString(String key) {
        return (String) fetch(key);
    }

    /**
     * Safely fetches a key.
     *
     * If the value is null, it will return an empty string.
     *
     * If it is a list, it will assume it is a list of strings and select a random value from it.
     *
     * Otherwise it will just return the value as a string.
     *
     * @param key
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String safeFetch(String key, String defaultIfNull) {
        Object o = fetchObject(key);
        if (o == null) return defaultIfNull;
        if (o instanceof List) {
            List<String> values = (List<String>) o;
            return values.get(randomService.nextInt(values.size()));
        } else {
            return (String) o;
        }
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key key contains path to an object. Path segment is separated by
     *            dot. E.g. name.first_name
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object fetchObject(String key) {
        String[] path = key.split("\\.");

        Object result = null;
        for (Map<String, Object> fakeValuesMap : fakeValuesMaps) {
            Object currentValue = fakeValuesMap;
            for (int p = 0; currentValue != null && p < path.length; p++) {
                currentValue = ((Map<String, Object>) currentValue).get(path[p]);
            }
            result = currentValue;
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p/>
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(randomService.nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @return
     */
    public String bothify(String string) {
        return letterify(numerify(string));
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String,boolean)}
     * over the incoming string.
     *
     * @param string
     * @param isUpper
     * @return
     */
    public String bothify(String string, boolean isUpper) {
        return letterify(numerify(string), isUpper);
    }

    /**
     * Generates a String that matches the given regular expression.
     */
    public String regexify(String regex) {
        Generex generex = new Generex(regex);
        generex.setSeed(randomService.nextLong());
        return generex.random();
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p/>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        return this.letterify(letterString, false);
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p/>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @param isUpper specifies whether or not letters should be upper case
     * @return
     */
    public String letterify(String letterString, boolean isUpper) {
        return letterHelper((isUpper) ? 65 : 97, letterString); // from ascii table
    }

    private String letterHelper(int baseChar, String letterString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letterString.length(); i++) {
            if (letterString.charAt(i) == '?') {
                sb.append((char) (baseChar + randomService.nextInt(26))); // a-z
            } else {
                sb.append(letterString.charAt(i));
            }
        }

        return sb.toString();
    }

    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("#\\{([A-Za-z_.]+)\\}");
    /**
     * Resolves a key to a method on an object.
     *
     * #{hello} with result in a method call to current.hello();
     *
     * #{Person.hello_someone} will result in a method call to person.helloSomeone();
     *
     */
    public String resolve(String key, Object current, Faker root) {
        final String template = safeFetch(key, null);
        return processTemplate(template, current, root);
    }

    /**
     * <p>processes a template in the style #{X.y} using the current objects as the 'current' location
     * within the yml file (or the {@link Faker} object hierarchy as it were).
     * </p>
     * <p>
     *     #{Address.streetName} would get resolved to {@link Faker#address()}'s {@link Address#streetName()}
     * </p>
     * <p>
     *     #{address.street} would get resolved to the YAML > locale: faker: address: street:
     * </p>
     * <p>
     *     Combinations are supported as well: "#{x} #{y}"
     * </p>
     * <p>
     *     Recursive templates are supported.  if "#{x}" resolves to "#{Address.streetName}" then "#{x}" resolves to
     *     {@link Faker#address()}'s {@link Address#streetName()}.
     * </p>
     */
    protected String processTemplate(String template, Object current, Faker root) {
        final Matcher matcher = EXPRESSION_PATTERN.matcher(template);

        String result = template;
        while (matcher.find()) {
            final String escapedDirective = matcher.group(0);
            final String directive = matcher.group(1);
            
            // resolve the expression and reprocess it to handle recursive templates
            String resolved = resolveExpression(directive, current, root);
            resolved = processTemplate(resolved, current, root);
            
            result = result.replace(escapedDirective, resolved);
        }
        return result;
    }

    /**
     * <h1>Search Order</h1>
     * <ul>
     *     <li>First search local keys</li>
     *     <li>Search for methods on the current object</li>
     *     <li>Search for methods on faker child objects</li>
     * </ul>
     * @param directive
     * @param current
     * @param root
     * @return
     */
    private String resolveExpression(String directive, Object current, Faker root) {
        // name.name (resolve locally)
        // Name.first_name (resolve to faker.name().firstName())
        final String simpleDirective = isNestedDirective(directive) 
                ? directive 
                : classNameToYamlName(current) + "." + directive;
        
        String resolved = safeFetch(simpleDirective, null);
        if (resolved == null && !isNestedDirective(directive)) {
            resolved = resolveFromMethodOnCurrent(current, directive);
        }
        if (resolved == null) {
            final String fakerChildObjectAndMethod = isNestedDirective(directive) 
                ? directive
                : current.getClass().getSimpleName() + "." + directive;
            resolved = resolveFakerObjectAndMethod(root, fakerChildObjectAndMethod);
        }
        return resolved;
    }

    private boolean isNestedDirective(String directive) {
        return directive.contains(".");
    }

    private String classNameToYamlName(Object current) {
        return current.getClass().getSimpleName()
                .replaceAll("([A-Z])", "_$1")
                .substring(1)
                .toLowerCase();
    }

    /**
     * Given a directive like 'firstName', attempts to resolve it to a method.  For example if current is an instance of
     * {@link Name} then this method would return {@link Name#firstName()}.  Returns null if the directive is nested
     * (i.e. has a '.') or the method doesn't exist on the <em>current</em> object.
     */
    private String resolveFromMethodOnCurrent(Object current, String directive) {
        try {
            return string(accessor(current,directive).invoke(current));
        }
        catch (Exception e) {
            log.log(Level.FINE, "Can't call " + directive + " on " + current, e);
            return null;
        }
    }


    /**
     * Accepts a {@link Faker} instance and a name.firstName style 'key' which is resolved to the return value of:
     * {@link Faker#name()}'s {@link Name#firstName()} method.
     * @throws RuntimeException if there's a problem invoking the method or it doesn't exist.
     */
    public String resolveFakerObjectAndMethod(Faker faker, String key) {
        final String[] classAndMethod = key.split("\\.", 2);
        
        try {
            Method fakerAccessor = accessor(faker, classAndMethod[0].replaceAll("_", ""));
            Object objectWithMethodToInvoke = fakerAccessor.invoke(faker);
            final Method accessor = accessor(objectWithMethodToInvoke, classAndMethod[1].replaceAll("_", ""));
            Object ret = accessor.invoke(objectWithMethodToInvoke);
            return ret == null ? null : ret.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find an accessor by name ignoring case.
     */
    private Method accessor(Object faker, String name) {
        Method fakerAccessor = null;
        for (Method m : faker.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase(name) && m.getParameterTypes().length == 0) {
                fakerAccessor = m;
                break;
            }
        }
        return fakerAccessor;
    }

    private String string(Object obj) {
        return (obj == null) ? null : obj.toString();
    }
}
