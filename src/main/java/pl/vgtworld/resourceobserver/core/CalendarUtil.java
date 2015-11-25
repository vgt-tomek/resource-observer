package pl.vgtworld.resourceobserver.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public final class CalendarUtil {

	private CalendarUtil() {
	}

	public static List<String> getWeekDayNames(Locale locale) {
		Calendar instance = Calendar.getInstance(locale);
		int firstDayOfWeek = instance.getFirstDayOfWeek();
		int currentDayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
		instance.add(Calendar.HOUR, -24 * (currentDayOfWeek - firstDayOfWeek));

		List<String> weekDayNames = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE", locale);
		for (int i = 0; i < 7; ++i) {
			weekDayNames.add(sdf.format(instance.getTime()));
			instance.add(Calendar.HOUR, 24);
		}
		return weekDayNames;
	}
}
