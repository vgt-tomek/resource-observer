package pl.vgtworld.resourceobserver.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.services.NotificationService;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.services.SnapshotService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.scan.Scan;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Singleton
public class ResourceScanner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceScanner.class);

	private static final int SCAN_INTERVAL_TOLERANCE = 15;

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	@EJB
	private SnapshotService snapshotService;

	@EJB
	private NotificationService notificationService;

	@Schedule(second = "0", minute = "*", hour = "*", persistent = false)
	public void scanResources() {
		LOGGER.debug("Scan resources event triggered");

		List<Resource> resources = resourceService.findAll();
		for (Resource resource : resources) {
			LOGGER.debug("Checking resource for scanning: {}", resource.getName());
			if (!resource.getActive()) {
				LOGGER.debug("Resource inactive. Skipping");
				continue;
			}

			Scan lastScan = scanService.findLastScanForResource(resource.getId());
			Scan lastSuccessfulScan = scanService.findLastSuccessfulScanForResource(resource.getId());
			LOGGER.debug("Last scan for resource: {}", lastScan);
			if (lastScan != null && !isOlderThan(lastScan, resource.getCheckInterval(), SCAN_INTERVAL_TOLERANCE)) {
				LOGGER.debug("Last scan not old enough. Skipping");
				continue;
			}
			LOGGER.info("Executing new scan for resource: {}.", resource.getName());
			byte[] resourceContext = downloadResource(resource);
			if (resourceContext == null) {
				LOGGER.info("Unable to download resource. Saving unsuccessful scan.");
				scanService.saveScanFailureForResource(resource.getId());
				continue;
			}
			String resourceHash = HashUtil.generateHash(resourceContext);
			LOGGER.info("Resource downloaded and marked with hash: {}", resourceHash);

			int snapshotId = snapshotService.findIdForSnapshot(resourceHash, resourceContext);
			scanService.saveScanSuccessForResource(resource.getId(), snapshotId);

			if (lastSuccessfulScan != null && snapshotId != lastSuccessfulScan.getSnapshotId()) {
				LOGGER.info(
					  "Version change detected. Create notification with version change {} => {}",
					  lastSuccessfulScan.getSnapshotId(),
					  snapshotId
				);
				notificationService.createNewNotificationForResourceChange(
					  resource.getId(),
					  lastSuccessfulScan.getSnapshotId(),
					  snapshotId
				);
			}
		}

	}

	private boolean isOlderThan(Scan scan, int checkInterval, int secondsTolerance) {
		long currentTime = new Date().getTime();
		long scanTime = scan.getCreatedAt().getTime();
		return scanTime + checkInterval * 60 * 1000 <= currentTime + secondsTolerance * 1000;
	}

	private byte[] downloadResource(Resource resource) {
		try {
			return DownloadUtil.downloadResource(resource.getUrl());
		} catch (IOException e) {
			LOGGER.debug("Downloading resource failed.", e);
			return null;
		}
	}

}
