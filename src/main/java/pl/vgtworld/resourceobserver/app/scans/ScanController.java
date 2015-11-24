package pl.vgtworld.resourceobserver.app.scans;

import com.googlecode.htmleasy.View;
import pl.vgtworld.resourceobserver.app.scans.models.resourcehistory.ResourceHistoryModel;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/scans")
public class ScanController {

	@EJB
	private ResourceService resourceService;

	@EJB
	private StatsService statsService;

	@GET
	@Path("/resource-history/{id}")
	public Response getScanHistoryForResource(@PathParam("id") int id) {
		Resource resource = resourceService.findById(id);

		if (resource == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}

		ResourceHistoryModel model = new ResourceHistoryModel();
		model.setResource(resource);
		model.setScans(statsService.findNewestScans(id, null));

		return Response.ok(new View("/views/scan-history-for-resource.jsp", model)).build();
	}
}
