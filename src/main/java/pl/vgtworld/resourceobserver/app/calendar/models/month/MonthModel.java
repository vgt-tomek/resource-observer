package pl.vgtworld.resourceobserver.app.calendar.models.month;

import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class MonthModel {

	private Resource resource;

	private MonthVersionsTable versionsMonthly;

	private List<Scan> scanLog;

	private String previousMonthLinkSuffix;

	private String nextMonthLinkSuffix;

	private String pageHeadTitle;

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

	public List<Scan> getScanLog() {
		return scanLog;
	}

	public void setScanLog(List<Scan> scanLog) {
		this.scanLog = scanLog;
	}

	public String getPreviousMonthLinkSuffix() {
		return previousMonthLinkSuffix;
	}

	public void setPreviousMonthLinkSuffix(String previousMonthLinkSuffix) {
		this.previousMonthLinkSuffix = previousMonthLinkSuffix;
	}

	public String getNextMonthLinkSuffix() {
		return nextMonthLinkSuffix;
	}

	public void setNextMonthLinkSuffix(String nextMonthLinkSuffix) {
		this.nextMonthLinkSuffix = nextMonthLinkSuffix;
	}

	public String getPageHeadTitle() {
		return pageHeadTitle;
	}

	public void setPageHeadTitle(String pageHeadTitle) {
		this.pageHeadTitle = pageHeadTitle;
	}

}
