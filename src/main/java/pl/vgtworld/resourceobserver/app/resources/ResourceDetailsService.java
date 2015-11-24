package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.services.StatsService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ResourceDetailsService {

	private static final int NEWEST_SCANS_COUNT = 10;

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	@EJB
	private StatsService statsService;

	public DetailsModel getResourceDetails(int resourceId) {
		Resource resource = resourceService.findById(resourceId);
		if (resource == null) {
			return null;
		}
		List<ResourceVersion> versions = statsService.findResourceVersions(resourceId);
		DetailsModel model = new DetailsModel();
		model.setResource(resource);
		model.setNewestScans(statsService.findNewestScans(resourceId, NEWEST_SCANS_COUNT));
		model.setScanCount(scanService.getScanCountForResource(resourceId));
		model.setVersions(versions);
		return model;
	}

}
