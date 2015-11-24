package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.app.resources.models.details.ResourceVersion;
import pl.vgtworld.resourceobserver.core.colortool.ColorGenerator;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
		model.setScanCount(scanService.getScanCountForResource(resourceId));
		model.setVersions(asModelVersions(scanService.findVersionsForResource(resourceId)));
		return model;
	}

	private List<ResourceVersion> asModelVersions(List<pl.vgtworld.resourceobserver.storage.scan.dto.ResourceVersion> versions) {
		List<ResourceVersion> output = new ArrayList<>();
		ColorGenerator colorGenerator = new ColorGenerator();
		List<Color> colors = colorGenerator.generateColors(versions.size());
		int count = 0;
		for (pl.vgtworld.resourceobserver.storage.scan.dto.ResourceVersion version : versions) {
			ResourceVersion convertedVersion = new ResourceVersion();
			convertedVersion.setBackgroundColor(colors.get(count));
			convertedVersion.setVersionId(++count);
			convertedVersion.setSnapshotId(version.getId());
			convertedVersion.setFirstOccurrence(version.getFirstOccurrence());
			convertedVersion.setLastOccurrence(version.getLastOccurrence());
			convertedVersion.setCount(version.getCount());
			output.add(convertedVersion);
		}
		return output;
	}

}
