package com.github.trustbox.logmap.pojo;

public class GlossDiv {
  private GlossList GlossList = new GlossList();

  private String title = "S";

  public GlossList getGlossList() {
    return GlossList;
  }

  public void setGlossList(GlossList GlossList) {
    this.GlossList = GlossList;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "ClassPojo [GlossList = " + GlossList + ", title = " + title + "]";
  }
}
