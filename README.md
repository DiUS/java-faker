Java Faker
==========

[![Maven Status](https://maven-badges.herokuapp.com/maven-central/com.github.javafaker/javafaker/badge.svg?style=flat)](http://mvnrepository.com/artifact/com.github.javafaker/javafaker)
[![Build Status](https://travis-ci.org/DiUS/java-faker.svg?branch=master)](https://travis-ci.org/DiUS/java-faker)
[![Coverage Status](https://coveralls.io/repos/DiUS/java-faker/badge.svg)](https://coveralls.io/r/DiUS/java-faker)
[![Dependency Status](https://www.versioneye.com/user/projects/572c2f11a0ca35004baf861a/badge.svg?style=flat)](https://www.versioneye.com/user/projects/572c2f11a0ca35004baf861a)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This library is a port of Ruby's stympy/faker gem (as well as Perl's Data::Faker library) that generates fake data.
It's useful when you're developing a new project and need some pretty data for showcase.

Usage
-----
In pom.xml, add the following xml stanza between `<dependencies> ... </dependencies>`

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>0.13</version>
</dependency>
```

For gradle users, add the following to your build.gradle file.

```groovy
repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'com.github.javafaker', name: 'javafaker', version: '0.13'
}

```

In your Java code

```java
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
```

Javadoc
-----
http://dius.github.io/java-faker/apidocs/index.html


Fakers
-----
* Ancient
* Address
* App
* Artist
* Beer
* Book
* Bool
* Business
* ChuckNorris
* Cat
* Code
* Color
* Commerce
* Company
* Crypto
* DateAndTime
* Demographic
* Educator
* Esports
* File
* Finance
* Food
* Friends
* GameOfThrones
* Hacker
* HarryPotter
* Hipster
* IdNumber
* Internet
* Job
* LordOfTheRings
* Lorem
* Music
* Name
* Number
* Options
* PhoneNumber
* Pokemon
* RockBand
* Shakespeare
* SlackEmoji
* Space
* Stock
* Superhero
* Team
* University
* Zelda

Usage with Locales
-----

```java
Faker faker = new Faker(new Locale("{YOUR_LOCALE}"));
```

Supported Locales
-----
* bg
* ca
* ca-CAT
* da-DK
* de
* de-AT
* de-CH
* en
* en-AU
* en-au-ocker
* en-BORK
* en-CA
* en-GB
* en-IND
* en-NEP
* en-NG
* en-NZ
* en-PAK
* en-SG
* en-UG
* en-US
* en-ZA
* es
* es-MX
* fa
* fi-FI
* fr
* he
* in-ID
* it
* ja
* ko
* nb-NO
* nl
* pl
* pt
* pt-BR
* ru
* sk
* sv
* sv-SE
* tr
* uk
* vi
* zh-CN
* zh-TW

TODO
----
- Port more classes over as there are more entries in the yml file that we don't have classes for

LICENSE
-------
Copyright (c) 2014 DiUS Computing Pty Ltd. See the LICENSE file for license rights and limitations.
