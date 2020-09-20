Java Faker
==========

[![Maven Status](https://maven-badges.herokuapp.com/maven-central/com.github.javafaker/javafaker/badge.svg?style=flat)](http://mvnrepository.com/artifact/com.github.javafaker/javafaker)
[![Build Status](https://travis-ci.org/DiUS/java-faker.svg?branch=master)](https://travis-ci.org/DiUS/java-faker)
[![Coverage Status](https://coveralls.io/repos/DiUS/java-faker/badge.svg)](https://coveralls.io/r/DiUS/java-faker)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This library is a port of Ruby's [faker](https://github.com/stympy/faker) gem (as well as Perl's Data::Faker library) that generates fake data.
It's useful when you're developing a new project and need some pretty data for showcase.

Usage
-----
In pom.xml, add the following xml stanza between `<dependencies> ... </dependencies>`

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.1</version>
</dependency>
```

For gradle users, add the following to your build.gradle file.

```groovy
dependencies {
    implementation 'com.github.javafaker:javafaker:1.0.1'
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

This is a [demo web application](https://java-faker.herokuapp.com/) that uses the library.

Javadoc
-----
http://dius.github.io/java-faker/apidocs/index.html


Android Setup Instructions
-----
Android studio uses gradle to build to project so you need to migrate maven project to gradle which could be done using following steps.
1. Clone the repository locally.
2. Migrate this Maven project to Gradle
    1. Open terminal and navigate to root of the project, where `pom.xml` is present,
    2. run `gradle init` command in terminal,
    3. It will ask you to migrate the existing maven project to gradle, select `yes`,
    4. run command `sh gradlew build` to make sure project is successfully migrated.
3. Import Module to Android Studio
    1. Open android studio project
    2. `Select file > new > import module > select java library`
    3. In wizard dialog, add library name : `javaFaker`, 
        package name : `com.github.javafaker` ,
        class name: `myClass`  
    4. In app gradle dependency add 
        ``` 
        dependencies{
            implementation project(path: ':javaFaker')
            ...
        }
        ```
    5. Copy `gradle.build` content from local migrated clone of library and paste it in `gradle.build` of javaFaker to your project.
    6. Copy paste all the files from local clone to the javaFaker module in your project.
    7. Sync and update your imports and Faker library would be available for use.
    


Contributions
-------------
See [CONTRIBUTING.md](https://github.com/DiUS/java-faker/blob/master/CONTRIBUTING.md)


Fakers
-----
* Address
* Ancient
* Animal
* App
* Aqua Teen Hunger Force
* Artist
* Avatar
* Back To The Future
* Aviation
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
* Dog
* DragonBall
* Dune
* Educator
* Esports
* File
* Finance
* Food
* Friends
* FunnyName
* GameOfThrones
* Hacker
* HarryPotter
* Hipster
* HitchhikersGuideToTheGalaxy
* Hobbit
* HowIMetYourMother
* IdNumber
* Internet
* Job
* LeagueOfLegends
* Lebowski
* LordOfTheRings
* Lorem
* Matz
* Music
* Name
* Nation
* Number
* Options
* Overwatch
* PhoneNumber
* Pokemon
* Princess Bride
* Relationship Terms
* RickAndMorty
* Robin
* RockBand
* Shakespeare
* SlackEmoji
* Space
* StarTrek
* Stock
* Superhero
* Team
* TwinPeaks
* University
* Weather
* Witcher
* Yoda
* Zelda

Usage with Locales
-----

```java
Faker faker = new Faker(new Locale("YOUR_LOCALE"));
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
* en-MS
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
Copyright (c) 2019 DiUS Computing Pty Ltd. See the LICENSE file for license rights and limitations.
