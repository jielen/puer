package com.ufgov.zc.common.system.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	public static Date add(Date date, int field, int day){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		calendar.add(field, day);
		
		return calendar.getTime();
		
	}

}
