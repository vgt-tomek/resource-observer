package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ResourceDetailsService {

	@EJB
	private ResourceService resourceService;

	public DetailsModel getResourceDetails(int resourceId) {
		Resource resource = resourceService.findById(resourceId);
		if (resource == null) {
			return null;
		}
		DetailsModel model = new DetailsModel();
		model.setResource(resource);
		return model;
	}

}
