package com.github.fcappi.log4splunk.test.pojo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.event.Level;

import com.github.fcappi.log4splunk.log.Loggable;

public class LoggableUser implements Loggable {

  private String username = "joedoe";
  private String firstName = "Joe";
  private String lastName = "Doe";
  private String password = "12345";
  private int age = 30;

  @Override
  public Map<String, String> logMe(Level level) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("user", username);
    
    return map;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}
