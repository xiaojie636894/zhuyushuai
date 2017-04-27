package com.java115.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 
	 * 
	 * @param datestr 
	 * @param formatestr	
	 * @return
	 */
	public static Date getDateByStr(String datestr,String formatestr){
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatestr);
		try {
			date = dateFormat.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String  getStrByDate(Date date,String formatestr){
		String str = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatestr);
		str = dateFormat.format(date);
	
		return str;
	}
	
	//--------------
	public static String getRandomFileName(String ext){
		String str = getStrByDate(new Date(), "yyyyMMddHHmmssms");
		for(int i=0;i<4;i++){
			str =str + (int)(Math.random()*10);
		}
		
		return str+ext;
	}

	public static String getRandomOrderID() {
		String str = getStrByDate(new Date(), "yyyyMMddHHmmssms");
		for(int i=0;i<4;i++){
			str =str + (int)(Math.random()*10);
		}
		
		return str;
	}
	
}
