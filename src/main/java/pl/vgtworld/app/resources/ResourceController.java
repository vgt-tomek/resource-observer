package pl.vgtworld.app.resources;

import com.googlecode.htmleasy.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ResourceController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getResourceList() {
		return new View("/views/resource-list.jsp");
	}

}
