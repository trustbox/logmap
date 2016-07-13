package com.github.fcappi.log4splunk.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import com.github.fcappi.log4splunk.utils.JsonModelConverter;


/**
 * Log Builder - Build logs using key-value pattern
 * 
 * @author Fernando Cappi (fcappi)
 */
public class LogBuilder {
  private String message;
  private Map<String, String> logMap;
  private Map<String, Loggable> loggableMap;
  private Map<String, Object> logAsJsonMap;
  private Level level;
  private final Logger LOGGER;

  /**
   * Construct a log builder that create a message to be logged on the given logger
   * 
   * @param logger to log messages
   */
  protected LogBuilder(Logger logger) {
    if (logger == null) {
      throw new IllegalArgumentException("Invalid parameter: logger can not be null");
    }

    LOGGER = logger;
  }

  /**
   * Init the log builder
   * 
   * @param message Log level message
   * @param level Log level
   * @return Log builder instance
   */
  private LogBuilder initBuilderLevel(String message, Level level) {
    if (this.level != null) {
      log();
    }
    this.logMap = new HashMap<String, String>();
    this.loggableMap = new HashMap<String, Loggable>();
    this.logAsJsonMap = new HashMap<String, Object>();
    this.message = message;
    this.level = level;

    return this;
  }

  /**
   * Init a log message builder as TRACE. Remember to call the method {@link #log()} after build the
   * message.
   * 
   * @param message Log level message
   * @return Log builder
   */
  public LogBuilder trace(String message) {
    return initBuilderLevel(message, Level.TRACE);
  }

  /**
   * Init a log message builder as DEBUG. Remember to call the method {@link #log()} after build the
   * message.
   * 
   * @param message Log level message
   * @return Log builder
   */
  public LogBuilder debug(String message) {
    return initBuilderLevel(message, Level.DEBUG);
  }

  /**
   * Init a log message builder as INFO. Remember to call the method {@link #log()} after build the
   * message.
   * 
   * @param message Log level message
   * @return Log builder
   */
  public LogBuilder info(String message) {
    return initBuilderLevel(message, Level.INFO);
  }

  /**
   * Init a log message builder as WARN. Remember to call the method {@link #log()} after build the
   * message.
   * 
   * @param message Log level message
   * @return Log builder
   */
  public LogBuilder warn(String message) {
    return initBuilderLevel(message, Level.WARN);
  }

  /**
   * Init a log message builder as ERROR. Remember to call the method {@link #log()} after build the
   * message.
   * 
   * @param message Log level message
   * @return Log builder
   */
  public LogBuilder error(String message) {
    return initBuilderLevel(message, Level.ERROR);
  }

  /**
   * Add the key=value to log message
   * 
   * @param key key
   * @param value value
   * @return Log builder to continue the build process
   */
  public LogBuilder add(String key, String value) {
    if (value != null) {
      logMap.put(key, value);
    }

    return this;
  }

  /**
   * Add the object that implements {@link Loggable} to log message. Will call the log method of the
   * object to get info to add to message.
   * 
   * @param loggableObject Object that implements {@link Loggable}
   * @return Log builder to continue the build process
   */
  public LogBuilder add(Loggable loggableObject) {
    if (loggableObject != null) {
      loggableMap.put(loggableObject.getClass().getSimpleName(), loggableObject);
    }

    return this;
  }

  /**
   * Add the object that implements {@link Loggable} to log message. Will call the log method of the
   * object to get info to add to message.
   * 
   * @param key key to map the value on log message
   * @param loggableObject Object that implements {@link Loggable}
   * @return Log builder to continue the build process
   */
  public LogBuilder add(String key, Loggable loggableObject) {
    if (loggableObject != null) {
      loggableMap.put(key, loggableObject);
    }

    return this;
  }

  /**
   * Add the object to log message as JSON string. <b>Be careful using this method! Only log objects
   * which structure you know</b>
   * 
   * @param key key to map the value on log message
   * @param objectToLogAsJson Object to transform to JSON string
   * @return Log builder to continue the build process
   */
  public LogBuilder add(String key, Object objectToLogAsJson) {
    if (objectToLogAsJson != null) {
      logAsJsonMap.put(key, objectToLogAsJson);
    }

    return this;
  }

  /**
   * Add the object to log message as JSON string. <b>Be careful using this method! Only log objects
   * which structure is know</b>
   * 
   * @param objectToLogAsJson Object to transform to JSON string
   * @return Log builder to continue the build process
   */
  public LogBuilder add(Object objectToLogAsJson) {
    if (objectToLogAsJson != null) {
      logAsJsonMap.put(objectToLogAsJson.getClass().getSimpleName(), objectToLogAsJson);
    }

    return this;
  }

  /**
   * Effectively log the message. Do not forget to call this method at the end of a log build
   * process
   */
  public void log() {
    if (level == Level.TRACE && LOGGER.isTraceEnabled()) {
      LOGGER.trace(logLevel(level));
    } else if (level == Level.DEBUG && LOGGER.isDebugEnabled()) {
      LOGGER.debug(logLevel(level));
    } else if (level == Level.INFO && LOGGER.isInfoEnabled()) {
      LOGGER.info(logLevel(level));
    } else if (level == Level.WARN && LOGGER.isWarnEnabled()) {
      LOGGER.warn(logLevel(level));
    } else if (level == Level.ERROR && LOGGER.isErrorEnabled()) {
      LOGGER.error(logLevel(level));
    }
  }

  /**
   * Build a log message for the current builder at the given level
   * 
   * @param level log level
   * @return log message
   */
  private String logLevel(Level level) {
    // log message
    StringBuffer str = new StringBuffer("msg=\"").append(message).append("\",");

    loggableMap.forEach((key, loggable) -> {
      logMap.putAll(loggable.logMe(level));
    });

    logAsJsonMap.forEach((key, logObject) -> {
      try {
        logMap.put(key, JsonModelConverter.toJsonString(logObject));
      } catch (Exception e) {
        logMap.put(key, logObject == null ? "" : logObject.toString());
      }
    });

    // key value
    logMap.forEach((key, value) -> {
      // space
      str.append(" ");

      Boolean containsSpace = value != null && value.contains(" ");
      // key=
      str.append(key).append("=");

      // value or "value_with_space"
      if (containsSpace) {
        str.append("\"").append(value).append("\"");
      } else {
        str.append(value);
      }

      // , comma
      str.append(",");
    });

    return str.toString();
  }
}
