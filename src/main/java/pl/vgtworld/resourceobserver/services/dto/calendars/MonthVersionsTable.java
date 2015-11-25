package pl.vgtworld.resourceobserver.services.dto.calendars;

import java.util.List;

public class MonthVersionsTable {

	private List<WeekVersionsRow> weeks;

	public List<WeekVersionsRow> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<WeekVersionsRow> weeks) {
		this.weeks = weeks;
	}

}
