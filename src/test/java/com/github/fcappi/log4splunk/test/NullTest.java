package com.github.fcappi.log4splunk.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.fcappi.log4splunk.log.CustomLogger;
import com.github.fcappi.log4splunk.test.pojo.Glossary;
import com.github.fcappi.log4splunk.test.pojo.LoggableUser;
/**
 * Test the code behavior when using null objects
 * @author Fernando Cappi (fcappi)
 *
 */
public class NullTest {
  private static CustomLogger L = new CustomLogger("test-logger");

  @Test
  public void testNullMessage() {
    L.debug(null).log();
  }

  @Test
  public void testNullKeys() {
    Glossary glossary = new Glossary();
    LoggableUser user = new LoggableUser();
    
    L.debug("Trying to log with null keys").add(null, "value").log();
    L.debug("Trying to log with null keys").add(null, glossary).log();
    L.debug("Trying to log with null keys").add(null, user).log();
  }
  
  @Test
  public void testNullValues() {
    Glossary glossary = null;
    LoggableUser user = null;
    String nullValue = null;
    Throwable nullError = null;
    
    L.debug("Trying to log with null values").add("object", nullValue).log();
    L.debug("Trying to log with null values").add("object", glossary).log();
    L.debug("Trying to log with null values").add("object", user).log();
    
    L.debug("Trying to log with null values without key").add(nullValue).log();
    L.debug("Trying to log with null values without key").add(glossary).log();
    L.debug("Trying to log with null values without key").add(user).log();
    L.debug("Trying to log with null values without key").add(nullError).log();
  }
  
  @Test
  public void testComplexObjectsFail() {
    Thread thread = Thread.currentThread();
    L.debug("Trying to log complex object").add("thread", thread).log();
  }
}

