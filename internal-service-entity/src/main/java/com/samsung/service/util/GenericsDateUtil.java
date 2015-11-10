package com.samsung.service.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class GenericsDateUtil {
	
	public static String getStringDateYYmmDD(String date) {
		
		String result = null;
		
		String year = date.substring(2, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		
		result = day+"/"+month+"/"+year;
		
		return result;
	}
	
	public static String getCurrentDate_yyyyMMdd() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String convertDateToString() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return formatter.format(Calendar.getInstance().getTime());				
	}	
}