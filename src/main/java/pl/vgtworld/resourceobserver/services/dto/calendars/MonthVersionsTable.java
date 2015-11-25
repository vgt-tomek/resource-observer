package pl.vgtworld.resourceobserver.services.dto.calendars;

import java.util.List;

public class MonthVersionsTable {

	private List<String> weekDayNames;

	private List<WeekVersionsRow> weeks;

	public List<String> getWeekDayNames() {
		return weekDayNames;
	}

	public void setWeekDayNames(List<String> weekDayNames) {
		this.weekDayNames = weekDayNames;
	}

	public List<WeekVersionsRow> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<WeekVersionsRow> weeks) {
		this.weeks = weeks;
	}

}
