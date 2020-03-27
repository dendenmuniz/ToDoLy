package com.novare.sda7.todoly.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <h2>Date Utils Class</h2>
 * <p>This class are used to deal with dates entries formating then to "MM/dd/yyyy". </p>
 * 
 * @author denisemuniz 
 * @version 1.0
 * @since 2020-03-06
 *
 */
public class DateUtils {

	private static final DateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	
	
	/**
	 * This method recieve a date and convet to string.
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Date date){
		return FORMATTER.format(date);
	}
	
	/**
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String dateString) throws ParseException {
		return FORMATTER.parse(dateString);
	}
	
}
