package pl.vgtworld.resourceobserver.services.dto.calendars;

import java.util.List;

public class WeekVersionsRow {

	private List<DayVersionsCell> days;

	public List<DayVersionsCell> getDays() {
		return days;
	}

	public void setDays(List<DayVersionsCell> days) {
		this.days = days;
	}

}
