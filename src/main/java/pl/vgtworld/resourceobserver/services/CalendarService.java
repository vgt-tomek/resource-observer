package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.calendars.DayVersionsCell;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.services.dto.calendars.WeekVersionsRow;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class CalendarService {

	@EJB
	private StatsService statsService;

	public MonthVersionsTable findResourceVersionsInMonth(int resourceId, int year, int month) {
		List<Scan> versions = statsService.findResourceVersionsInMonth(resourceId, year, month);
		Map<Integer, List<Scan>> versionsByDay = groupVersionsByDayOfMonth(versions);
		int days = getNumberOfDaysInMonth(year, month);

		int currentlyProcessedWeekDay = 0;
		MonthVersionsTable monthContainer = new MonthVersionsTable();
		monthContainer.setWeeks(new ArrayList<>());
		WeekVersionsRow weekContainer = new WeekVersionsRow();
		for (int i = 1; i <= days; ++i) {
			++currentlyProcessedWeekDay;
			if (currentlyProcessedWeekDay == 1) {
				weekContainer = new WeekVersionsRow();
				weekContainer.setDays(new ArrayList<>());
			}
			DayVersionsCell dayCell = new DayVersionsCell();
			dayCell.setDayOfMonth(i);
			dayCell.setVersions(getUniqueVersionList(versionsByDay.get(i)));
			weekContainer.getDays().add(dayCell);
			if (currentlyProcessedWeekDay == 7 || i == days) {
				monthContainer.getWeeks().add(weekContainer);
				currentlyProcessedWeekDay = 0;
			}
		}
		while (weekContainer.getDays().size() < 7) {
			weekContainer.getDays().add(new DayVersionsCell());
		}
		return monthContainer;
	}

	private List<ResourceVersion> getUniqueVersionList(List<Scan> scans) {
		if (scans == null) {
			return new ArrayList<>();
		}
		Map<Integer, ResourceVersion> versionsById = new LinkedHashMap<>();
		for (Scan scan : scans) {
			if (scan.getVersion() == null) {
				continue;
			}
			versionsById.put(scan.getVersion().getSnapshotId(), scan.getVersion());
		}
		return versionsById.keySet().stream().map(versionsById::get).collect(Collectors.toList());
	}

	private int getNumberOfDaysInMonth(int year, int month) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	private Map<Integer, List<Scan>> groupVersionsByDayOfMonth(List<Scan> versions) {
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
