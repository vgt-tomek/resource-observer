package pl.vgtworld.resourceobserver.app.calendar;

import pl.vgtworld.resourceobserver.app.calendar.models.month.MonthModel;
import pl.vgtworld.resourceobserver.services.CalendarService;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Stateless
public class MonthCalendarService {

	@EJB
	private CalendarService calendarService;

	@EJB
	private StatsService statsService;

	public MonthModel getModelForResourceCalendarMonthPage(Resource resource, int year, int month) {
		MonthModel model = new MonthModel();
		model.setScanLog(statsService.findResourceVersionsInMonth(resource.getId(), year, month));
		model.setResource(resource);
		model.setVersionsMonthly(calendarService.findResourceVersionsInMonth(resource.getId(), year, month));
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
		model.setPageHeadTitle(resource.getName() + " - " + sdf.format(calendar.getTime()));
		calendar.add(Calendar.MONTH, -1);
		model.setPreviousMonthLinkSuffix(getUrlSuffix(resource.getId(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
		calendar.add(Calendar.MONTH, 2);
		model.setNextMonthLinkSuffix(getUrlSuffix(resource.getId(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
		return model;
	}

	private String getUrlSuffix(int resourceId, int year, int month) {
		return "/" + resourceId + "/" + year + "/" + month;
	}

}
