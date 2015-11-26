package pl.vgtworld.resourceobserver.app.calendar;

import com.googlecode.htmleasy.View;
import pl.vgtworld.resourceobserver.app.calendar.models.month.MonthModel;
import pl.vgtworld.resourceobserver.services.CalendarService;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resource-calendar")
public class CalendarController {

	@EJB
	private ResourceService resourceService;

	@EJB
	private CalendarService calendarService;

	@GET
	@Path("/{id}/{year}/{month}")
	@Produces(MediaType.TEXT_HTML)
	public Response getMonthCalendar(@PathParam("id") int resourceId, @PathParam("year") int year, @PathParam("month") int month) {
		if (month < 1 || month > 12 || year <= 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		Resource resource = resourceService.findById(resourceId);
		if (resource == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		MonthModel model = new MonthModel();
		model.setResource(resource);
		model.setVersionsMonthly(calendarService.findResourceVersionsInMonth(resourceId, year, month));

		return Response.ok(new View("/views/resource-calendar-month.jsp", model)).build();
	}
}
