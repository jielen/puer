package com.ufgov.zc.client.util;

public class PwdUtil {
  /**
   * 口令加密与解密
   * 
   * @param passwd
   * @return
   * 
   */
  public static String _encodePwd(String passwd) {

    String encodeStr = "$#TGDF*FAA&21we@VGXD532w23413!";
    String tempStr = "";
    if (passwd == null) {
      passwd = "";
    }

    int i;
    for (i = 0; i < passwd.length(); i++) {
      tempStr = tempStr + (char) (passwd.charAt(i) ^ encodeStr.charAt(i));
    }

    return tempStr;
  }
  public static void main(String[] args){
    String name="dt5";
    System.out.println(PwdUtil._encodePwd(name));
  }
}
