package pl.vgtworld.resourceobserver.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public final class CalendarUtil {

	private static final int DAYS_IN_WEEK = 7;

	private static final String SHORT_WEEK_NAME_PATTERN = "EEE";

	private CalendarUtil() {
	}

	public static List<String> getWeekDayNames(Locale locale) {
		Calendar instance = Calendar.getInstance(locale);
		int firstDayOfWeek = instance.getFirstDayOfWeek();
		int currentDayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
		instance.add(Calendar.HOUR, -24 * (currentDayOfWeek - firstDayOfWeek));

		List<String> weekDayNames = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat(SHORT_WEEK_NAME_PATTERN, locale);
		for (int i = 0; i < 7; ++i) {
			weekDayNames.add(sdf.format(instance.getTime()));
			instance.add(Calendar.HOUR, 24);
		}
		return weekDayNames;
	}

	public static int getEmptyDaysInFirstWeekOfMonth(int year, int month, Locale locale) {
		Calendar instance = Calendar.getInstance(locale);
		instance.set(Calendar.YEAR, year);
		instance.set(Calendar.MONTH, month - 1);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		int firstDayOfWeek = instance.getFirstDayOfWeek();
		int dayOfWeekForFirstDayOfMonth = instance.get(Calendar.DAY_OF_WEEK);
		int result = dayOfWeekForFirstDayOfMonth - firstDayOfWeek;
		if (result < 0) {
			result += DAYS_IN_WEEK;
		}
		return result;
	}

	public static int getNumberOfDaysInMonth(int year, int month) {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR, year);
		instance.set(Calendar.MONTH, month - 1);
		return instance.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

}
