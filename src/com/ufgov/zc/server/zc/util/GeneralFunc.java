package com.ufgov.zc.server.zc.util;

import java.security.MessageDigest;

public class GeneralFunc {


	  public static String encodePwd(String passwd) {
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

	  public static String _encodePwd(String paramString) {
	    /*String str1 = "$#TGDF*FAA&21we@VGXD532w23413!";
	    String str2 = "";
	    if (paramString == null) paramString = "";
	    for (int i = 0; i < paramString.length(); i++)
	      str2 = str2 + (char) (paramString.charAt(i) ^ str1.charAt(i));
	    return str2;*/
	    return encodePwd(paramString);
	  }

	  public static String recodePwd(String encodedPasswd) {
		  String encodeStr = "$#TGDF*FAA&21we@VGXD532w23413!";
		    String tempStr = "";
		    if (encodedPasswd == null) {
		      encodedPasswd = "";
		    }

		    int i;
		    for (i = 0; i < encodedPasswd.length(); i++) {
		      char truePass = (char) ~(encodedPasswd.charAt(i) ^ ~encodeStr.charAt(i));
		      tempStr = tempStr + truePass;
		    }

		    return tempStr;
	  }
 

	  public static void main(String[] args) {
	    String name = "abcdefg";
	    String s1 = GeneralFunc.encodePwd(name);
	    String s2 = GeneralFunc.recodePwd("]K6prpqpsQV");
	    System.out.println(name);
	    System.out.println(s1);
	    System.out.println(s2);

	    String t1 = com.anyi.gp.pub.GeneralFunc.encodePwd(name);
	    String t2 = com.anyi.gp.pub.GeneralFunc.recodePwd(t1);
	    System.out.println(t1);
	    System.out.println(t2);
	  }
	}

