package pl.vgtworld.resourceobserver.app.diff;

import com.googlecode.htmleasy.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.diff.models.form.FormModel;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("/diff/{resourceId}")
public class DiffController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiffController.class);

	@PathParam("resourceId")
	private String resourceId;

	@EJB
	private ResourceService resourceService;

	@EJB
	private StatsService statsService;

	@GET
	public Response displayDiffForm() throws URISyntaxException {
		LOGGER.debug("display diff form for resouce: {}", resourceId);

		Resource resource = findResource();
		if (resource == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		List<ResourceVersion> versions = statsService.findResourceVersions(resource.getId());
		FormModel model = new FormModel();
		model.setResource(resource);
		model.setVersions(versions);

		return Response.ok(new View("/views/diff-form.jsp", model)).build();
	}

	private Resource findResource() {
		try {
			int convertedResourceId = Integer.parseInt(resourceId);
			return resourceService.findById(convertedResourceId);
		} catch (NumberFormatException e) {
			LOGGER.trace("Resource id is not a number.", e);
			return null;
		}
	}
}
