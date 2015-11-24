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
		int newestScanCount = getNewestScanCountRoundedUp(versions);
		model.setNewestScans(statsService.findNewestScans(resourceId, newestScanCount));
		model.setScanCount(scanService.getScanCountForResource(resourceId));
		model.setVersions(versions);
		return model;
	}

	private int getNewestScanCountRoundedUp(List<ResourceVersion> versions) {
		return (versions.size() + 10) / 10 * 10;
	}

}
