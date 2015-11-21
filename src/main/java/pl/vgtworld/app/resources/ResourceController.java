package pl.vgtworld.app.resources;

import com.googlecode.htmleasy.View;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.app.resources.models.list.ListModel;
import pl.vgtworld.storage.resource.ResourceService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ResourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@EJB
	private ResourceService resourceService;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getResourceList() {
		ListModel model = new ListModel();
		model.setResources(resourceService.findAll());
		return new View("/views/resource-list.jsp", model);
	}

	@GET
	@Path("/create-resource")
	@Produces(MediaType.TEXT_HTML)
	public View createResourceForm() {
		return new View("/views/resource-create-form.jsp");
	}

	@POST
	@Path("/create-resource")
	@Produces(MediaType.TEXT_HTML)
	public View createResource(@Form ResourceFormDto resource) {
		LOGGER.debug("Creating new resource: {}", resource);

		if (resource != null && resource.getResourceFormCancel() != null) {
			LOGGER.debug("New resource action canceled. Redirecting.");
			return new View("/views/resource-create-cancel.jsp");
		}

		//TODO Validation
		//TODO Storage

		return new View("/views/resource-create-success.jsp");
	}

}
