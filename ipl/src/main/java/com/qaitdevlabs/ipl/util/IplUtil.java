package com.qaitdevlabs.ipl.util;

import java.util.Calendar;
import java.util.Date;

public class IplUtil {
	
	public static Date covnvertDateToIST(Date dateInGMT) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateInGMT);
		calendar.add(Calendar.HOUR_OF_DAY, 4);
		calendar.add(Calendar.MINUTE, 30);
		return calendar.getTime();
	}

	public static Date covnvertDateToGMT(Date dateInIST) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateInIST);
		calendar.add(Calendar.HOUR_OF_DAY, -4);
		calendar.add(Calendar.MINUTE, -30);
		return calendar.getTime();
	}
}
