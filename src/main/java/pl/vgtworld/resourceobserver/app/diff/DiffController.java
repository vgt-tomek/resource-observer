package pl.vgtworld.resourceobserver.app.diff;

import com.googlecode.htmleasy.View;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.diff.dto.DiffFormDto;
import pl.vgtworld.resourceobserver.app.diff.models.form.FormModel;
import pl.vgtworld.resourceobserver.app.diff.validator.DiffValidator;
import pl.vgtworld.resourceobserver.core.diffutil.DiffUtil;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	@EJB
	private SnapshotService snapshotService;

	@GET
	public Response displayDiffForm() throws URISyntaxException {
		LOGGER.debug("display diff form for resource: {}", resourceId);

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

	@POST
	public Response submitDiffForm(@Form DiffFormDto form) throws URISyntaxException {
		LOGGER.debug("submit diff form: {} for resource: {}", form, resourceId);

		Resource resource = findResource();
		if (resource == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		List<ResourceVersion> versions = statsService.findResourceVersions(resource.getId());
		FormModel model = new FormModel();
		model.setResource(resource);
		model.setVersions(versions);
		model.setLeftVersionSelected(form.getFirst());
		model.setRightVersionSelected(form.getSecond());

		DiffValidator validator = new DiffValidator(snapshotService, versions);
		DiffValidator.Result validationResult = validator.validate(form);

		if (validationResult.isValid()) {
			model.setDiffLines(DiffUtil.createColorCodedUnifiedDiff(
				  validationResult.getFirstSnapshot().getResource(),
				  validationResult.getSecondSnapshot().getResource(),
				  "Version #" + form.getFirst(),
				  "Version #" + form.getSecond()
			));
		} else {
			model.setErrors(validationResult.getErrors());
		}
		return Response.ok(new View("/views/diff-form.jsp", model)).build();
	}

	@GET
	@Path("/{leftVersion}/{rightVersion}")
	public Response displayDiff(@PathParam("leftVersion") String leftVersion, @PathParam("rightVersion") String rightVersion) throws URISyntaxException {
		DiffFormDto form = new DiffFormDto();
		form.setFirst(leftVersion);
		form.setSecond(rightVersion);
		return submitDiffForm(form);
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
