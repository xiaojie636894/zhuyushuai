package com.java115.utils;

import java.util.Date;

public class OrderUtils {
	public static String makeOrderNums(){
	String datenum = "";
	datenum = DateUtils.getStrByDate(new Date(), "yyyyMMddHHmmss");
	String num = ""+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10);
	return datenum+num;
	}
}