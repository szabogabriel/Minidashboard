package com.github.szabogabriel.minidashboard.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.WeakHashMap;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	private static Map<String, DateTimeFormatter> formatterCache = new WeakHashMap<>();

	public static LocalDateTime fromMillies(Long millies) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(millies), ZoneId.systemDefault());
	}

	public static String fromMillies(Long millies, String format) {
		LocalDateTime ldt = fromMillies(millies);
		return ldt.format(getFormatter(format));
	}

	private static DateTimeFormatter getFormatter(String format) {
		if (!formatterCache.containsKey(format)) {
			formatterCache.put(format, DateTimeFormatter.ofPattern(format));
		}
		return formatterCache.get(format);
	}

}
