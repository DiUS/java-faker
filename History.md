Java Faker

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
