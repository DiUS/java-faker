First and foremost thanks to anyone who contributes, very much appreciated.


## Guidelines
- Need to maintain compatibility with Java 1.6 (and Android). Therefore any attempt to migrate to 1.6 JDKs and above will not be merged back in.

- If you add new faker classes like `Address`, `Country`, and `Number` they should be accompanied by a unit test. Where relevant please add more assertions to the `com.github.javafaker.integration.FakerIT` class.
- If you add a new faker class, update the `README.md`.
- Submit a PR with your change and if there are no comments, changes will be merged in
- If you're not sure about the change, raise an issue and have a discussion before spending time coding it up
- Try and make one logical change per PR. That is not make many changes in one PR. Submit multiple PRs instead

## Building

- Should be as easy as running `mvn clean install` on the root directory
