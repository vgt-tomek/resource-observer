package pl.vgtworld.resourceobserver.app.resources.models.details;

import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class DetailsModel {

	private Resource resource;

	private List<Scan> newestScans;

	private Long scanCount;

	private List<ResourceVersion> versions;

	private MonthVersionsTable versionsMonthly;

	private String calendarLinkSuffix;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Scan> getNewestScans() {
		return newestScans;
	}

	public void setNewestScans(List<Scan> newestScans) {
		this.newestScans = newestScans;
	}

	public Long getScanCount() {
		return scanCount;
	}

	public void setScanCount(Long scanCount) {
		this.scanCount = scanCount;
	}

	public List<ResourceVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<ResourceVersion> versions) {
		this.versions = versions;
	}

	public MonthVersionsTable getVersionsMonthly() {
		return versionsMonthly;
	}

	public void setVersionsMonthly(MonthVersionsTable versionsMonthly) {
		this.versionsMonthly = versionsMonthly;
	}

	public String getCalendarLinkSuffix() {
		return calendarLinkSuffix;
	}

	public void setCalendarLinkSuffix(String calendarLinkSuffix) {
		this.calendarLinkSuffix = calendarLinkSuffix;
	}

}
