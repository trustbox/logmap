#Log4Splunk [![Build Status](https://travis-ci.org/fcappi/log4splunk.svg?branch=master)](https://travis-ci.org/fcappi/log4splunk)

**Log4Splunk** is a tool to help you generate logs in the format [Splunk](http://www.splunk.com/en_us/products/splunk-enterprise.html) expects.

**Log4Splunk IS NOT** a logging framework. It just build messages to be logged by any logging framework that implements [Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/) (e.g. Log4J, Logback, java.util.logging)

It follows the [Logging Best Practices](http://dev.splunk.com/view/logging-best-practices/SP-CAAADP6) defined by Splunk team. 

Since **Log4Splunk** just generate log message in a standard format, you can use it even if you are not using Splunk.


## Code Example 

First, get an instance of the **CustomLogger** class

```java
private static CustomLogger L = new CustomLogger(MyClassName.class); 
```
And use it to log your application events:

__Log an event passing a message and adding useful info__

```java
L.error("Error! Log it!").add("error_code", 502).log();
```
```
21:31:00.598 [main] ERROR test-logger - msg="Error! Log it!", error_code=502
```


__You can add as many key/values entries as you want__

```java
L.error("Error! Log it!").add("error_code", 502).add("error_message", ex.getMessage()).log();
```

```
21:31:00.598 [main] ERROR test-logger - msg="Error! Log it!", error_code=502, error_message="Unexpected exception"
```


__You can also log an object as JSON__

```java
L.debug("Logging the User Object as JSON").add("user", userObject).log(); 
```

```
21:31:00.598 [main] DEBUG test-logger - msg="Logging the User Object as JSON", user="{id:123, name:"Joe Doe", age:35}"
```


__And an exception stack trace__

```java
L.error("Error! See this stack trace").add(myException).log(); 
```

```
21:31:00.598 [main] ERROR test-logger - msg="Error! See this stack trace", stacktrace=java.lang.Exception
	at com.github.fcappi.log4splunk.test.NullTest.testNullValues(NullTest.java:48)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)"
```

## Installation

If you use Maven, add **Log4Splunk** as a dependency in your project including the following lines to your pom.xml:

```
<dependency>
    <groupId>com.github.fcappi</groupId>
    <artifactId>log4splunk</artifactId>
    <version>1.1.0</version>
</dependency>
```

If you use other dependency management tools, see [Maven repository - Dependency information](http://search.maven.org/#artifactdetails%7Ccom.github.fcappi%7Clog4splunk%7C1.0.0%7Cjar)

It's also required that you have as your project dependency a logging framework that implements [Simple Logging Facade for Java (SLF4J)](http://www.slf4j.org/) (e.g. Log4J, Logback, java.util.logging).

Done! Now see the [Code Example](#code-example) section to know how easy and useful is to use **Log4Splunk**!

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE v3 - see the [LICENSE.md](LICENSE.md) file for details
