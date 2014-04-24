Java Faker

0.4
---
* Some API changes

    * fetch/fetchString/fetchObject methods are no longer accessible from the Faker class

    * Preferred way to access data generation methods is not directly from the Faker class

So

```
faker.name().firstName(); // preferred
```

instead of

```
faker.firstName(); // deprecated
```

This also means when there are clashes like

```
faker.phoneNumber(); // deprecated
```

This needs to be invoked

```
faker.phoneNumber().phoneNumber(); // preferred
```

In a subsequent release, the methods directly off faker will be removed in favour
of the methods on the helper classes.


* Added email address
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
