package com.github.trustbox.logmap.pojo;

public class Glossary {
  private String title = "example glossary";

  private GlossDiv GlossDiv = new GlossDiv();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public GlossDiv getGlossDiv() {
    return GlossDiv;
  }

  public void setGlossDiv(GlossDiv GlossDiv) {
    this.GlossDiv = GlossDiv;
  }

  @Override
  public String toString() {
    return "ClassPojo [title = " + title + ", GlossDiv = " + GlossDiv + "]";
  }
}
