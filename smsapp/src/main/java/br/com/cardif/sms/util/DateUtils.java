package br.com.cardif.sms.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * DateUtils
 * 
 * This class provide utilities to date manipulation
 * 
 * @author Rodrigo Portela da Costa
 *
 */
public final class DateUtils {
	
	public static SimpleDateFormat FULLCALENDAR_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Day of Week Names
	 */
	public static List<String> WEEKDAYNAMES;
	
	/**
	 * Month names
	 */
	public static List<String> MONTHNAMES;
	
	static {
		
		WEEKDAYNAMES = new ArrayList<String>();
		Collections.addAll(WEEKDAYNAMES, "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado");
		
		MONTHNAMES = new ArrayList<String>();
		Collections.addAll(MONTHNAMES, "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez");
	}
	
	private DateUtils(){
		
	}
	
	/**
	 * Create a new date with the provided date
	 * setting time to 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date begin(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}
	
	/**
	 * Create a new date with the provided date
	 * setting time to 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date end(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}
	
	/**
	 * Create an array with all date values in each positions
	 * 
	 * Positions:
	 * 0 - Day of month
	 * 1 - Month
	 * 2 - Year
	 * 3 - Hour
	 * 4 - Minute
	 * 5 - Second
	 * 6 - Day of week
	 * 7 - Day of week in month
	 * 
	 * @param date
	 * @return
	 */
	public static int[] split(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int[] array = new int[]{
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				calendar.get(Calendar.DAY_OF_WEEK),
				calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)};
		return array;
	}
	
	/**
	 * Create an empty date with only provided hours, minutes and seconds
	 * 
	 * @param h
	 * @param m
	 * @param s
	 * @return
	 */
	public static Date getDateWithTimeOnly(int h, int m, int s){
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.HOUR_OF_DAY, h);
		calendar.set(Calendar.MINUTE, m);
		calendar.set(Calendar.SECOND, s);
		calendar.set(Calendar.MILLISECOND, h);
		return calendar.getTime();
	}
		
	/**
	 * Return a date wich is in compliance with primefaces schedule API.
	 * 
	 * @param date
	 * @return
	 */
	public static String fullCalendarDate(Date date) {
		return FULLCALENDAR_FORMATTER.format(date).replace(" ", "T");
	}
	
	/**
	 * Return the day of week id based on name provided
	 * @param name
	 * @return
	 */
	public static Integer weekDay(String name){
		return WEEKDAYNAMES.indexOf(name);
	}
	
	/**
	 * Return the month id based on name provided
	 * @param name
	 * @return
	 */
	public static Integer month(String name){
		return MONTHNAMES.indexOf(name);
	}
	
	/**
	 * Return an interval in milliseconds since begining of day
	 * from a provided date
	 * 
	 * @param date
	 * @return
	 */
	public static Long intervalMilliseconds(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Long interval = 0l;
		interval += calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000;
		interval += calendar.get(Calendar.MINUTE) * 60 * 1000;
		return interval;
	}

	/**
	 * Convert a interval in milliseconds to an representative date
	 * 
	 * @param millis
	 * @return
	 */
	public static Date intervalDate(Long millis){
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.add(Calendar.MILLISECOND, millis.intValue());
		return calendar.getTime();
	}
	
	/**
	 * Return a date wich represents when the execution should end
	 * This is used to determine if an execution is running late or not
	 * 
	 * @param executionDate
	 * @param elapsedTime
	 * @return
	 */
	public static Date executionElapsedDate(Date executionDate, Date elapsedTime){
		
		Calendar elapsedCalendar = new GregorianCalendar();
		elapsedCalendar.setTime(elapsedTime);
		
		Calendar executionCalendar = new GregorianCalendar();
		executionCalendar.setTime(executionDate);
		executionCalendar.add(Calendar.HOUR_OF_DAY, elapsedCalendar.get(Calendar.HOUR_OF_DAY));
		executionCalendar.add(Calendar.MINUTE, elapsedCalendar.get(Calendar.MINUTE));
		executionCalendar.add(Calendar.SECOND, elapsedCalendar.get(Calendar.SECOND));
		return executionCalendar.getTime();
	}

}
