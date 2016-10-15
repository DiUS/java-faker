package com.github.javafaker.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.ClassUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeValuesService {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("#\\{([a-zA-Z_.]+)\\s?(?:'([^']+)')?(?:,'([^']+)')*\\}");

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

    /**
     * Resolves a key to a method on an object.
     *
     * #{hello} with result in a method call to current.hello();
     *
     * #{Person.hello_someone} will result in a method call to person.helloSomeone();
     *
     */
    public String resolve(String key, Object current, Faker root) {
        final String expression = safeFetch(key, null);
        if (expression == null) {
            throw new RuntimeException(key + " resulted in null expression");
        }

        return resolveExpression(expression, current, root);
    }

    /**
     * resolves an expression using the current faker.
     * @param expression
     * @param faker
     * @return
     */
    public String expression(String expression, Faker faker) {
        return resolveExpression(expression, null, faker);
    }
    /**
     * <p>processes a expression in the style #{X.y} using the current objects as the 'current' location
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
    protected String resolveExpression(String expression, Object current, Faker root) {
        final Matcher matcher = EXPRESSION_PATTERN.matcher(expression);

        String result = expression;
        while (matcher.find()) {
            final String escapedDirective = matcher.group(0);
            final String directive = matcher.group(1);
            List<String> args = new ArrayList<String>();
            for (int i=2;i < matcher.groupCount()+1 && matcher.group(i) != null;i++) {
                args.add(matcher.group(i));
            }
            
            // resolve the expression and reprocess it to handle recursive templates
            String resolved = resolveExpression(directive, args, current, root);
            if (resolved == null) {
                throw new RuntimeException("Unable to resolve " + escapedDirective + " directive.");
            }

            resolved = resolveExpression(resolved, current, root);
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
     * @return null if unable to resolve
     */
    private String resolveExpression(String directive, List<String> args, Object current, Faker root) {
        // name.name (resolve locally)
        // Name.first_name (resolve to faker.name().firstName())
        final String simpleDirective = (isDotDirective(directive) || current == null) 
                ? directive 
                : classNameToYamlName(current) + "." + directive;
        
        // simple fetch of a value from the yaml file. the directive may have been mutated
        // such that if the current yml object is car: and directive is #{wheel} then 
        // car.wheel will be looked up in the YAML file.
        String resolved = safeFetch(simpleDirective, null);

        // resolve method references on CURRENT object like #{number_between '1','10'} on Number or
        // #{ssn_valid} on IdNumber
        if (resolved == null && !isDotDirective(directive)) {
            resolved = resolveFromMethodOn(current, directive, args);
        }

        // resolve method references on faker object like #{regexify '[a-z]'}
        if (resolved == null && !isDotDirective(directive)) {
            resolved = resolveFromMethodOn(root, directive, args);
        }

        // Resolve Faker Object method references like #{ClassName.method_name}
        if (resolved == null && isDotDirective(directive)) {
            resolved = resolveFakerObjectAndMethod(root, directive, args);
        }
        
        return resolved;
    }

    private boolean isDotDirective(String directive) {
        return directive.contains(".");
    }

    private String classNameToYamlName(Object current) {
        return current.getClass().getSimpleName()
                .replaceAll("([A-Z])", "_$1")
                .substring(1)
                .toLowerCase();
    }

    /**
     * Given a directive like 'firstName', attempts to resolve it to a method.  For example if obj is an instance of
     * {@link Name} then this method would return {@link Name#firstName()}.  Returns null if the directive is nested
     * (i.e. has a '.') or the method doesn't exist on the <em>obj</em> object.
     */
    private String resolveFromMethodOn(Object obj, String directive, List<String> args) {
        if (obj == null) {
            return null;
        }
        try {
            Method accessor = accessor(obj, directive, args);
            if (accessor == null) {
                return null;
            }
            // coerce the string arguments into the correct argument types
            List<Object> coerced = null;
            try  {
                coerced = coerceArguments(accessor, args);
            } catch (RuntimeException re) {
              log.log(Level.FINE, "Unable to coerce arguments : " + re.getMessage());  
            }
            return (accessor == null || coerced == null)
                    ? null
                    : string(accessor.invoke(obj, coerced.toArray()));
        } catch (Exception e) {
            log.log(Level.FINE, "Can't call " + directive + " on " + obj, e);
            return null;
        }
    }
    
    /**
     * Accepts a {@link Faker} instance and a name.firstName style 'key' which is resolved to the return value of:
     * {@link Faker#name()}'s {@link Name#firstName()} method.
     * @throws RuntimeException if there's a problem invoking the method or it doesn't exist.
     */
    private String resolveFakerObjectAndMethod(Faker faker, String key, List<String> args) {
        final String[] classAndMethod = key.split("\\.", 2);
        
        try {
            String fakerMethodName = classAndMethod[0].replaceAll("_", "");
            Method fakerAccessor = accessor(faker, fakerMethodName, Collections.<String>emptyList());
            if (fakerAccessor == null) {
                throw new RuntimeException("Can't find top level faker object named " + fakerMethodName + ".");
            }
            Object objectWithMethodToInvoke = fakerAccessor.invoke(faker);
            String nestedMethodName = classAndMethod[1].replaceAll("_", "");
            final Method accessor = accessor(objectWithMethodToInvoke, classAndMethod[1].replaceAll("_", ""), args);
            if (accessor == null) {
                throw new RuntimeException("Can't find method on " 
                        + objectWithMethodToInvoke.getClass().getSimpleName() 
                        + " called " + nestedMethodName + ".");
            }
            final List<Object> coerced = coerceArguments(accessor, args);
            
            Object ret = accessor.invoke(objectWithMethodToInvoke, coerced.toArray());

            return ret == null ? null : ret.toString();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            return null;
        }
    }

    
    /**
     * Find an accessor by name ignoring case.
     */
    private Method accessor(Object onObject, String name, List<String> args) {
        log.log(Level.FINE, "Find accessor named " + name + " on " + onObject.getClass().getSimpleName() + " with args " + args);
        Method fakerAccessor = null;
        for (Method m : onObject.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase(name) && m.getParameterTypes().length == args.size()) {
                fakerAccessor = m;
                break;
            }
        }
        if (fakerAccessor == null && name.contains("_")) {
            fakerAccessor = accessor(onObject, name.replaceAll("_", ""), args);
        }
        return fakerAccessor;
    }

    /**
     * Coerce arguments in <em>args</em> into the appropriate types (if possible) for the parameter arguments
     * to <em>accessor</em>.
     * @return array of coerced values if successful, null otherwise
     * @throws Exception if unable to coerce
     */
    private List<Object> coerceArguments(Method accessor, List<String> args) {
        final List<Object> coerced = new ArrayList<Object>();
        for (int i = 0; i < accessor.getParameterTypes().length; i++) {

            Class<?> toType = ClassUtils.primitiveToWrapper(accessor.getParameterTypes()[i]);
            try {
                final Constructor<?> ctor = toType.getConstructor(String.class);
                final Object coercedArgument = ctor.newInstance(args.get(i));

                coerced.add(coercedArgument);
            } catch (Exception e) {
                throw new RuntimeException("Unable to coerce " + args.get(i) + " to " + toType.getSimpleName() + " via " + toType.getSimpleName() + "(String) constructor.");
            }
        }
        return coerced;
    }
    
    private String string(Object obj) {
        return (obj == null) ? null : obj.toString();
    }
}
