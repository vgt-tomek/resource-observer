package pl.vgtworld.resourceobserver.app.scantrigger;

import com.googlecode.htmleasy.View;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.scantrigger.dto.ScanTriggerFormDto;
import pl.vgtworld.resourceobserver.app.scantrigger.validator.ScanTriggerValidator;
import pl.vgtworld.resourceobserver.services.storage.ResourceScanTriggerService;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/manual-scan-trigger")
public class ManualScanTriggerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManualScanTriggerController.class);

	@EJB
	private ResourceService resourceService;

	@EJB
	private ResourceScanTriggerService triggerService;

	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response addManualScanTriggerForResource(@Form ScanTriggerFormDto form) throws URISyntaxException {
		LOGGER.debug("Create manual scan trigger executed: {}", form);

		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);
		ScanTriggerValidator.Result validationResult = validator.validate(form);

		if (validationResult.isValid()) {
			LOGGER.debug("Validation successful. Creating new trigger.");
			triggerService.createNewScanTrigger(validationResult.getResourceId());
			return Response.seeOther(new URI("/")).build();
		} else {
			LOGGER.debug("Validation failed. Errors: {}", validationResult.getErrors());
			return Response.ok(new View("/views/scan-trigger-error.jsp")).build();
		}
	}

}
