package com.app.app.utility;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AppUtil {

	public static String getDateToTimeStamp(String reportedDate) {
		Date parse = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		try {
			parse = format.parse(reportedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		return formatter.format(parse);
	}

	
	public static List<String> getTimeIntervals(String timeProvided){
		String startTime = String.valueOf(Instant.parse(timeProvided));
		String secondTime = String.valueOf(Instant.parse(timeProvided).plus(43200,ChronoUnit.SECONDS));
		return Arrays.asList(startTime,secondTime);
	}
}
