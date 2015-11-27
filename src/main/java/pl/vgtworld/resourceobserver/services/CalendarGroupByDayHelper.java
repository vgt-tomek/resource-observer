package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.services.dto.Scan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CalendarGroupByDayHelper {

	Map<Integer, List<Scan>> groupVersionsByDayOfMonth(List<Scan> versions) {
		Map<Integer, List<Scan>> result = new HashMap<>();

		for (Scan version : versions) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(version.getCreatedAt());
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

			if (result.get(dayOfMonth) == null) {
				result.put(dayOfMonth, new ArrayList<>());
			}
			result.get(dayOfMonth).add(version);
		}
		return result;
	}

}
