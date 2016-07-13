package com.github.fcappi.log4splunk.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.event.Level;

/**
 * Loggable interface. All objects implementing this class can define which data will be added to
 * logs when it is passed as a {@link LogBuilder} method param.
 * 
 * @author Fernando Cappi (fcappi)
 *
 */
public interface Loggable {


  /**
   * Return a map of data to be logged
   * 
   * @param level Message log level
   * @return data map to be logged
   */
  default Map<String, String> logMe(Level level) {
    return new HashMap<String, String>();
  }
}
