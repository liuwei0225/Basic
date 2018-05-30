package com.example.demo;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.example")
public class FooProperties {
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  private boolean enabled;

  private String userName;
  
  private Map<String, String> map;

  public Map<String, String> getMap() {
	return map;
}

public void setMap(Map<String, String> map) {
	this.map = map;
}

@Override
public String toString() {
	return "FooProperties [enabled=" + enabled + ", userName=" + userName + ", map=" + map + "]";
}

public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
