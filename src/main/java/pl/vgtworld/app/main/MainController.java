package pl.vgtworld.app.main;

import com.googlecode.htmleasy.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getMainage() {
		return new View("/views/main.jsp");
	}

}
