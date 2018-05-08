package com.github.trustbox.logmap.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a custom logger that adapts log messages using key=value pattern. All application log
 * should use this class instead of Logback {@link Logger} class.
 * 
 * @author Fernando Cappi (fcappi)
 * @version %I%, %G%
 * @since 1.0.0
 */
public class CustomLogger {
  /** Logback LOGGER instance used to log messages **/
  private final Logger LOGGER;
  private static final String DEFAULT_LOGGER_NAME = "default";

  /**
   * Create a new Logger instance
   * 
   * @param clazz Class which name will be used as logger name. If null, will use the default value
   *        ({@link CustomLogger#DEFAULT_LOGGER_NAME})
   */
  @SuppressWarnings("rawtypes")
  public CustomLogger(Class clazz) {
    if (clazz == null) {
      LOGGER = LoggerFactory.getLogger(DEFAULT_LOGGER_NAME);
    } else {
      LOGGER = LoggerFactory.getLogger(clazz);
    }
  }

  /**
   * Create a new Logger instance
   * 
   * @param loggerName Logger name If null, will use the default value (
   *        {@link CustomLogger#DEFAULT_LOGGER_NAME})
   */
  public CustomLogger(String loggerName) {
    if (loggerName == null) {
      LOGGER = LoggerFactory.getLogger(DEFAULT_LOGGER_NAME);
    } else {
      LOGGER = LoggerFactory.getLogger(loggerName);
    }
  }

  /**
   * Init a log message build as TRACE level
   * 
   * @param logMessage log message
   * @return Log message builder
   */
  public LogBuilder trace(String logMessage) {
    return new LogBuilder(LOGGER).trace(logMessage);
  }

  /**
   * Init a log message build as DEBUG level
   * 
   * @param logMessage log message
   * @return Log message builder
   */
  public LogBuilder debug(String logMessage) {
    return new LogBuilder(LOGGER).debug(logMessage);
  }

  /**
   * Init a log message build as INFO level
   * 
   * @param logMessage log message
   * @return Log message builder
   */
  public LogBuilder info(String logMessage) {
    return new LogBuilder(LOGGER).info(logMessage);
  }

  /**
   * Init a log message build as WARN level
   * 
   * @param logMessage log message
   * @return Log message builder
   */
  public LogBuilder warn(String logMessage) {
    return new LogBuilder(LOGGER).warn(logMessage);
  }

  /**
   * Init a log message build as ERROR level
   * 
   * @param logMessage log message
   * @return Log message builder
   */
  public LogBuilder error(String logMessage) {
    return new LogBuilder(LOGGER).error(logMessage);
  }

  /**
   * Get the wrapped LogBack logger instance
   * 
   * @return Logback Logger instance
   */
  public Logger getLogger() {
    return LOGGER;
  }
}
