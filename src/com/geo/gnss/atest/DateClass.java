package com.geo.gnss.atest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateClass {


	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = sDateFormat.parse("2017-09-13");
		
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(currentDate);
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
		System.out.println(calendar.get(Calendar.YEAR));
	}

}
