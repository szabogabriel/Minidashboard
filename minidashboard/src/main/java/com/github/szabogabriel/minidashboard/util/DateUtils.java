package com.github.szabogabriel.minidashboard.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	public static LocalDateTime fromMillies(Long millies) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(millies), ZoneId.systemDefault());
	}
	
}
