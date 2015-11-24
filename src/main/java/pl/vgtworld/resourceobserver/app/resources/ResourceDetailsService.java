package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel;
import pl.vgtworld.resourceobserver.app.resources.models.details.ResourceVersion;
import pl.vgtworld.resourceobserver.core.colortool.ColorGenerator;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.scan.Scan;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<ResourceVersion> versions = asModelVersions(scanService.findVersionsForResource(resourceId));
		DetailsModel model = new DetailsModel();
		model.setResource(resource);
		model.setNewestScans(asScansDto(scanService.findNewestScans(resourceId, NEWEST_SCANS_COUNT), versions));
		model.setScanCount(scanService.getScanCountForResource(resourceId));
		model.setVersions(versions);
		return model;
	}

	private List<pl.vgtworld.resourceobserver.dto.scans.Scan> asScansDto(List<Scan> scans, List<ResourceVersion> versions) {
		Map<Integer, ResourceVersion> versionsBySnapshotId = new HashMap<>();
		for (ResourceVersion version : versions) {
			versionsBySnapshotId.put(version.getSnapshotId(), version);
		}

		List<pl.vgtworld.resourceobserver.dto.scans.Scan> output = new ArrayList<>();
		for (Scan scan : scans) {
			pl.vgtworld.resourceobserver.dto.scans.Scan dto = new pl.vgtworld.resourceobserver.dto.scans.Scan();
			dto.setCreatedAt(scan.getCreatedAt());
			dto.setVersion(versionsBySnapshotId.get(scan.getSnapshotId()));
			output.add(dto);
		}
		return output;
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
