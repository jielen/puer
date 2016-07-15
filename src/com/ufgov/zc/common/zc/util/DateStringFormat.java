package com.ufgov.zc.common.zc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringFormat {

  private static String normalFormat = "yyyy-MM-dd";
  private static DateFormat dateFormat;


  public static String Date2String(Date date, String format) {
      String currentFormat = format == null || "".equals(format) ? normalFormat : format;
      dateFormat = new SimpleDateFormat(currentFormat);
      try {
          return dateFormat.format(date);
      } catch (Exception e) {
          return "";
      }
  }

  public static String Date2String(Date date) {
      dateFormat = new SimpleDateFormat(normalFormat);
      try {
          return dateFormat.format(date);
      } catch (Exception e) {
          return "";
      }
  }

  public static Date String2Date(String dateString, String format) {
      String currentFormat = format == null || "".equals(format) ? normalFormat : format;
      dateFormat = new SimpleDateFormat(currentFormat);
      try {
          return dateFormat.parse(dateString);
      } catch (Exception e) {
          return null;
      }
  }

  public static Date String2Date(String dateString) {
      dateFormat = new SimpleDateFormat(normalFormat);
      try {
          return dateFormat.parse(dateString);
      } catch (Exception e) {
          return null;
      }
  }
}
