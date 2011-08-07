Java Faker
==========
This library is a port of Ruby's stympy/faker gem (as well as Perl's Data::Faker library) that generates fake data.
It's useful when you're developing a new project and need some pretty data for showcase.

Usage
-----
```java
Faker faker = new Faker();

String name = faker.name(); // Miss Samanta Schmidt
String firstName = faker.firstName(); // Emory
String lastName = faker.firstName(); // Barton

String streetAddress = faker.streetAddress(); // 60018 Sawayn Brooks Suite 449
```

How to build
------------
1. Make sure Apache Maven 3 is installed. ($ mvn --version)
2. $ mvn package
