package pl.vgtworld.resourceobserver.app.resources;

import com.googlecode.htmleasy.View;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.resources.models.form.FormModel;
import pl.vgtworld.resourceobserver.app.resources.models.list.ListModel;
import pl.vgtworld.resourceobserver.app.resources.validator.ResourceValidator;
import pl.vgtworld.resourceobserver.app.resources.validator.ValidationResult;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.resourceobserver.ResourceObserver;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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

		LOGGER.debug("Validating resource.");
		ResourceValidator validator = new ResourceValidator(resourceService);
		ValidationResult result = validator.validateNew(resource);
		if (!result.isValid()) {
			LOGGER.debug("Validation failed.");
			FormModel model = new FormModel();
			model.setErrors(result.getErrors());
			model.setResource(resource);
			return new View("/views/resource-create-form.jsp", model);
		}
		LOGGER.debug("Validation successful.");
		resourceService.createNewResource(result.getCreatedResource());

		return new View("/views/resource-create-success.jsp");
	}

	@GET
	@Path("/edit-resource/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response editResourceForm(@PathParam("id") int id) {
		Resource resourceEntity = resourceService.findById(id);

		if (resourceEntity == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}

		List<ResourceObserver> observers = resourceService.findAllObserversForResource(id);

		FormModel model = new FormModel();
		model.setErrors(new ArrayList<>());
		model.setResource(asResourceFormDto(resourceEntity, observers));
		model.setResourceId(id);

		return Response.ok(new View("/views/resource-edit-form.jsp", model)).build();
	}

	@POST
	@Path("/edit-resource/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response editResource(@PathParam("id") int id, @Form ResourceFormDto resource) {
		LOGGER.debug("Edit resource #{}: {}", id, resource);

		Resource resourceEntity = resourceService.findById(id);
		if (resourceEntity == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}

		if (resource != null && resource.getResourceFormCancel() != null) {
			LOGGER.debug("Edit resource action canceled. Redirecting.");
			return Response.ok(new View("/views/resource-edit-cancel.jsp")).build();
		}

		LOGGER.debug("Validating resource.");
		ResourceValidator validator = new ResourceValidator(resourceService);
		ValidationResult result = validator.validateEdit(resource, id);
		if (!result.isValid()) {
			LOGGER.debug("Validation failed.");
			FormModel model = new FormModel();
			model.setErrors(result.getErrors());
			model.setResource(resource);
			model.setResourceId(id);
			return Response.ok(new View("/views/resource-edit-form.jsp", model)).build();
		}
		LOGGER.debug("Validation successful.");
		//TODO Update resource

		return Response.ok(new View("/views/resource-edit-success.jsp")).build();
	}

	private ResourceFormDto asResourceFormDto(Resource entity, List<ResourceObserver> observers) {
		ResourceFormDto dto = new ResourceFormDto();
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setActive(entity.getActive() ? "on" : null);
		dto.setCheckInterval("" + entity.getCheckInterval());
		StringBuilder observersDto = new StringBuilder();
		for (ResourceObserver observer : observers) {
			observersDto.append("\n").append(observer.getEmail());
		}
		dto.setObservers(observersDto.toString().trim());
		return dto;
	}

}
