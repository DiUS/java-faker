Java Faker

0.8 (unreleased)
--
- Added emailaddress with local-part parameter to internet
- Deleted countryService/country, using address.country() and address.countryCode() instead because of the desire
to reuse existing yaml files

```
faker.internet().emailAddress("john"); //john@gmail.com
```
- Added a lot more YML files from the ruby faker version

0.7
--
- Added city from address

```
faker.address().city();
```

0.6
---
- Added detailed credit card numbers that pass the luhn check.

```
faker.finance().creditCard();
```

- Added avatar support

```
faker.internet().avatar();
```

- Added date and time support

```
faker.date().past(1, TimeUnit.SECONDS, now);
```

0.5
---
- IDN support
- Stripped out quotes for domain names
- Added timezone support for addresses

0.4
---
* Some API changes

    * fetch/fetchString/fetchObject methods are no longer accessible from the Faker class

    * New way to access data generation methods is not directly from the Faker class

So

```
faker.name().firstName(); // preferred
```

instead of

```
faker.firstName(); // deleted
```


* Added email address in
```
faker.internet().emailAddress();
```
* Finnish locale support
* Ability to pass in Random object to control seeding

```
new Faker(random);
```

* Credit card faker data added

```
new Faker().business().creditCardNumber();
```

* Migrated over to using SnakeYAML instead of JYaml as the latter is no longer being maintained
* Bit of an internal refactor
* Added fixedString
```
faker.lorem().fixedString(int);
```

* Added the ability to generate URLs.
```
faker.internet().url();
```

* Added the ability to get one option of a input set
```
faker.options().option(String[]);
```

=======
* Added latitude and longitude

```
new Faker().address().latitude();
```

```
new Faker().address().longitude();
```
