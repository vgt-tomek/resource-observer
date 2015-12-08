package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.list.ResourceModel;
import pl.vgtworld.resourceobserver.core.CalendarUtil;
import pl.vgtworld.resourceobserver.services.storage.ResourceScanTriggerService;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.services.storage.ScanService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.scan.Scan;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceListService {

	private static final int DEFAULT_NEW_FLAG_HOUR_THRESHOLD = 24;

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	@EJB
	private ResourceScanTriggerService triggerService;

	public List<ResourceModel> getResourceList() {
		List<Resource> entities = resourceService.findAll();
		return entities.stream().map(this::asResourceModel).collect(Collectors.toList());
	}

	private ResourceModel asResourceModel(Resource entity) {
		ResourceModel dto = new ResourceModel();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setActive(entity.getActive());
		dto.setCheckInterval(entity.getCheckInterval());
		dto.setLastCheckAt(getLastCheckForResource(entity.getId()));
		dto.setLastSeenAt(getLastSeenForResource(entity.getId()));
		dto.setDistinctSnapshotsCount(scanService.getUniqueSnapshotsCountForResource(entity.getId()));
		Date lastVersionChange = scanService.getLastVersionChange(entity.getId());
		dto.setLastVersionChange(lastVersionChange);
		dto.setNewFlag(calculateIsNewFlagForResource(lastVersionChange));
		dto.setScanTriggerPending(triggerService.findActiveScanTriggerForResource(entity.getId()) != null);
		return dto;
	}

	private Date getLastSeenForResource(int resourceId) {
		Scan lastSeenScan = scanService.findLastSuccessfulScanForResource(resourceId);
		if (lastSeenScan == null) {
			return null;
		}
		return lastSeenScan.getCreatedAt();
	}

	private Date getLastCheckForResource(int resourceId) {
		Scan lastScan = scanService.findLastScanForResource(resourceId);
		if (lastScan == null) {
			return null;
		}
		return lastScan.getCreatedAt();
	}

	private boolean calculateIsNewFlagForResource(Date lastVersionChange) {
		return lastVersionChange != null && CalendarUtil.isDateRangeWithinHourThreshold(lastVersionChange, new Date(), DEFAULT_NEW_FLAG_HOUR_THRESHOLD);
	}
}
