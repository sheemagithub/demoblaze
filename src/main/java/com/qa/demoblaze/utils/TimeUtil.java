package com.qa.demoblaze.utils;

import java.time.LocalTime;

public class TimeUtil {

	public static String getCurrentTime() {
		LocalTime currentTime = LocalTime.now();
		return String.valueOf(currentTime);
	}
	
}
