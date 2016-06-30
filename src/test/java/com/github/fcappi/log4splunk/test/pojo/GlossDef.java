package com.github.fcappi.log4splunk.test.pojo;

public class GlossDef {
  private String[] GlossSeeAlso = {"GML", "XML"};

  private String para = "A meta-markup language, used to create markup languages such as DocBook.";

  public String[] getGlossSeeAlso() {
    return GlossSeeAlso;
  }

  public void setGlossSeeAlso(String[] GlossSeeAlso) {
    this.GlossSeeAlso = GlossSeeAlso;
  }

  public String getPara() {
    return para;
  }

  public void setPara(String para) {
    this.para = para;
  }

  @Override
  public String toString() {
    return "ClassPojo [GlossSeeAlso = " + GlossSeeAlso + ", para = " + para + "]";
  }
}
