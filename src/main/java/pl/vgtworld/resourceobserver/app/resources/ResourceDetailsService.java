package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.services.CalendarService;
import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.services.storage.ScanService;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Stateless
public class ResourceDetailsService {

	private static final String URL_SEPARATOR = "/";

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	@EJB
	private StatsService statsService;

	@EJB
	private CalendarService calendarService;

	public DetailsModel getResourceDetails(int resourceId) {
		Resource resource = resourceService.findById(resourceId);
		if (resource == null) {
			return null;
		}
		List<ResourceVersion> versions = statsService.findResourceVersions(resourceId);
		DetailsModel model = new DetailsModel();
		model.setResource(resource);
		int newestScanCount = getNewestScanCountRoundedUp(versions);
		model.setNewestScans(statsService.findNewestScans(resourceId, newestScanCount));
		model.setScanCount(scanService.getScanCountForResource(resourceId));
		model.setVersions(versions);
		model.setVersionsMonthly(getVersionsForCurrentMonth(resourceId));
		model.setCalendarLinkSuffix(createLinkForCurrentMonth());
		return model;
	}

	private static int getNewestScanCountRoundedUp(List<ResourceVersion> versions) {
		return (versions.size() + 10) / 10 * 10;
	}

	private static String createLinkForCurrentMonth() {
		Calendar calendar = new GregorianCalendar();
		return URL_SEPARATOR + calendar.get(Calendar.YEAR) + URL_SEPARATOR + (calendar.get(Calendar.MONTH) + 1);
	}

	private MonthVersionsTable getVersionsForCurrentMonth(int resourceId) {
		Calendar calendar = new GregorianCalendar();
		return calendarService.findResourceVersionsInMonth(resourceId, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
	}

}
