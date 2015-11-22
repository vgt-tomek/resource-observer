package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ResourceDetailsService {

	private static final int NEWEST_SCANS_COUNT = 10;

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	public DetailsModel getResourceDetails(int resourceId) {
		Resource resource = resourceService.findById(resourceId);
		if (resource == null) {
			return null;
		}
		DetailsModel model = new DetailsModel();
		model.setResource(resource);
		model.setNewestScans(scanService.findNewestScans(resourceId, NEWEST_SCANS_COUNT));
		return model;
	}

}
