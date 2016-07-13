#Log4Splunk [![Build Status](https://travis-ci.org/fcappi/log4splunk.svg?branch=master)](https://travis-ci.org/fcappi/log4splunk)

Log4Splunk is a tool to help you generate logs in the format Splunk expects.

It follows the [Logging Best Practices](http://dev.splunk.com/view/logging-best-practices/SP-CAAADP6) defined by Splunk team. 


## Code Example

Get a instance of the CustomLogger class

` private static CustomLogger L = new CustomLogger("test-logger"); `

Log a event passing a message and useful info:

` L.error("Error! Log it!").add("error_code", 502).log(); `

You can add as many key/values entries as you want:

` L.error("Error! Log it!").add("error_code", 502).add("error_message", ex.getMessage()).log(); `

You can log an object as JSON:

` L.info("Logging the User Object as JSON").add("user", userObject).log(); `

## Installation

If you use Maven, include the following lines to your pom.xml:

```
<dependency>
    <groupId>com.github.fcappi</groupId>
    <artifactId>log4splunk</artifactId>
    <version>1.1.0</version>
</dependency>
```

If you use other dependency management tools, see [Maven repository - Dependency information](http://search.maven.org/#artifactdetails%7Ccom.github.fcappi%7Clog4splunk%7C1.0.0%7Cjar)

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE v3 - see the [LICENSE.md](LICENSE.md) file for details
