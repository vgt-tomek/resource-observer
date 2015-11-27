package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.dto.Scan;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CalendarUniqueVersionsHelper {

	List<ResourceVersion> extractUniqueVersionListBySnapshotId(List<Scan> scans) {
		if (scans == null) {
			return new ArrayList<>();
		}
		Map<Integer, ResourceVersion> versionsById = new LinkedHashMap<>();
		for (Scan scan : scans) {
			if (scan.getVersion() == null) {
				continue;
			}
			versionsById.put(scan.getVersion().getSnapshotId(), scan.getVersion());
		}
		return versionsById.keySet().stream().map(versionsById::get).collect(Collectors.toList());
	}

}
