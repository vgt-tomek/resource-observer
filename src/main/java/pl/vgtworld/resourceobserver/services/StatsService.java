package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.app.resources.models.details.ResourceVersion;
import pl.vgtworld.resourceobserver.core.colortool.ColorGenerator;
import pl.vgtworld.resourceobserver.dto.scans.Scan;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class StatsService {

	@EJB
	private ScanService scanService;

	public List<ResourceVersion> findResourceVersions(int resourceId) {
		return asModelVersions(scanService.findVersionsForResource(resourceId));
	}

	public List<Scan> findNewestScans(int resourceId, int count) {
		return asScansDto(scanService.findNewestScans(resourceId, count), findResourceVersions(resourceId));
	}

	private List<Scan> asScansDto(List<pl.vgtworld.resourceobserver.storage.scan.Scan> scans, List<ResourceVersion> versions) {
		Map<Integer, ResourceVersion> versionsBySnapshotId = new HashMap<>();
		for (ResourceVersion version : versions) {
			versionsBySnapshotId.put(version.getSnapshotId(), version);
		}

		List<pl.vgtworld.resourceobserver.dto.scans.Scan> output = new ArrayList<>();
		for (pl.vgtworld.resourceobserver.storage.scan.Scan scan : scans) {
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
