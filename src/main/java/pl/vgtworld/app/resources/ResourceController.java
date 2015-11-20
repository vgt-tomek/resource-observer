package pl.vgtworld.app.resources;

import com.googlecode.htmleasy.View;
import pl.vgtworld.storage.resource.ResourceService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ResourceController {

	@EJB
	private ResourceService resourceService;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getResourceList() {
		ListModel model = new ListModel();
		model.setResources(resourceService.findAll());
		return new View("/views/resource-list.jsp", model);
	}

}
