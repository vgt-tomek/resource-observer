package pl.vgtworld.resourceobserver.app.calendar.models.month;

import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

public class MonthModel {

	private Resource resource;

	private MonthVersionsTable versionsMonthly;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public MonthVersionsTable getVersionsMonthly() {
		return versionsMonthly;
	}

	public void setVersionsMonthly(MonthVersionsTable versionsMonthly) {
		this.versionsMonthly = versionsMonthly;
	}

}
