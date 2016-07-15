package com.ufgov.zc.client.util.freemark;

public class StringUtil {

  public static final String FU_HAO_GOU="√";
  public static final String FU_HAO_KUANG="□";
  /**
   * freemark填充word时特殊字符要转换，否则word将不能打开
   * @param str
   * @return
   */
  public static String freeMarkFillWordChar(String str){
    if(str == null){
      return "";
    }
    return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  }
}
