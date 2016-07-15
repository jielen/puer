package com.ufgov.zc.client.sf.entrust;

public class StringTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TCJLODO Auto-generated method stub
    String s = "需要补充或者重新提取鉴定材料的，延长$$个工作日；";
    String s1 = s.substring(0, s.indexOf("$$"));
    String s2 = s.substring(s.indexOf("$$") + 2);
    System.out.println(s1);
    System.out.println(s2);
  }

}
