package com.ufgov.zc.server.zc.util;

import java.security.MessageDigest;

public class GeneralFunc {

  public static String encodePwd(String paramString) {
    String str = null;
    try {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < arrayOfByte.length; i++)
        if ((0xFF & arrayOfByte[i]) < 16)
          localStringBuffer.append("0" + Integer.toHexString(0xFF & arrayOfByte[i]));
        else
          localStringBuffer.append(Integer.toHexString(0xFF & arrayOfByte[i]));
      str = localStringBuffer.toString();
    } catch (Exception localException) {
      localException.printStackTrace();
    }
    return str;
  }

  public static String _encodePwd(String paramString) {
    String str1 = "$#TGDF*FAA&21we@VGXD532w23413!";
    String str2 = "";
    if (paramString == null)
      paramString = "";
    for (int i = 0; i < paramString.length(); i++)
      str2 = str2 + (char) (paramString.charAt(i) ^ str1.charAt(i));
    return str2;
  }

  public static String recodePwd(String paramString) {
    String str1 = "$#TGDF*FAA&21we@VGXD532w23413!";
    String str2 = "";
    if (paramString == null)
      paramString = "";
    for (int i = 0; i < paramString.length(); i++) {
      char c = (char) (paramString.charAt(i) ^ (str1.charAt(i) ^ 0xFFFFFFFF) ^ 0xFFFFFFFF);
      str2 = str2 + c;
    }
    return str2;
  }
  public static String _encodePwd2(String passwd) {

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
    System.out.println(GeneralFunc._encodePwd2(name));
  }
}
