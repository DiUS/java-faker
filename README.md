Java Faker
==========

[![Build Status](https://travis-ci.org/DiUS/java-faker.png?branch=master)](https://travis-ci.org/DiUS/java-faker)

[![Coverage Status](https://coveralls.io/repos/DiUS/java-faker/badge.png)](https://coveralls.io/r/DiUS/java-faker)

This library is a port of Ruby's stympy/faker gem (as well as Perl's Data::Faker library) that generates fake data.
It's useful when you're developing a new project and need some pretty data for showcase.

Usage
-----
In pom.xml, add following between `<dependencies> ... </dependencies>`

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>0.5</version>
</dependency>
```

In your Java code

```java
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
```

How to build
------------
1. Make sure Apache Maven 3 is installed. ($ mvn --version)
2. $ mvn package


LICENSE
-------
Copyright (c) 2014 DiUS Computing Pty Ltd. See the LICENSE file for license rights and limitations.
