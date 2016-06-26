package com.github.fcappi.log4splunk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <b>JSON converter</b>
 * 
 * @author Fernando Cappi (fcappi)
 */
public class JsonModelConverter {
  /** JSON Converter **/
  private static Gson jsonConverter;

  /**
   * Returns a JSON string representing the <b>objectToConvert</b>
   * 
   * @param objectToConvert Object to be converted to JSON string
   * @return
   */
  public static String toJsonString(Object objectToConvert) {
    if (objectToConvert == null) {
      throw new IllegalArgumentException("JSON Parser Error: objectToConvert cannot be null");
    }

    try {
      return jsonConverter().toJson(objectToConvert);
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException(
          "JSON Parser Error: objectToConvert has circular dependency or is too big to convert to JSON",
          e);
    }
  }

  /**
   * Get JSON Converter
   * 
   * @return GSON converter
   */
  public static Gson jsonConverter() {
    if (jsonConverter == null) {
      initConverter();
    }

    return jsonConverter;
  }

  /**
   * Initializes Converter
   */
  private static void initConverter() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    jsonConverter = gsonBuilder.create();
  }
}
