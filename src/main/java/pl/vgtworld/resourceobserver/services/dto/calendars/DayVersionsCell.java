package pl.vgtworld.resourceobserver.services.dto.calendars;

import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;

import java.util.List;

public class DayVersionsCell {

	private Integer dayOfMonth;

	private List<ResourceVersion> versions;

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public List<ResourceVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<ResourceVersion> versions) {
		this.versions = versions;
	}

}
