package pl.vgtworld.resourceobserver.services.storage;

import pl.vgtworld.resourceobserver.storage.scan.Scan;
import pl.vgtworld.resourceobserver.storage.scan.ScanDao;
import pl.vgtworld.resourceobserver.storage.scan.dto.ResourceVersion;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class ScanService {

	@EJB
	private ScanDao scanDao;

	public Scan findLastScanForResource(int resourceId) {
		List<Scan> results = scanDao.findNewestForResource(resourceId, 1);
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

	public Scan findLastSuccessfulScanForResource(int resourceId) {
		List<Scan> results = scanDao.findNewestSuccessfulForResource(resourceId, 1);
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

	public List<Scan> findNewestScans(int resourceId, Integer count) {
		return scanDao.findNewestForResource(resourceId, count);
	}

	public List<Scan> findSnapshotsFromTimeFrameForResource(int resourceId, Date startTime, Date endTime) {
		return scanDao.findSnapshotsFromTimeFrameForResource(resourceId, startTime, endTime);
	}

	public List<ResourceVersion> findVersionsForResource(int resourceId) {
		return scanDao.findVersionsForResource(resourceId);
	}

	public Long getUniqueSnapshotsCountForResource(int resourceId) {
		return scanDao.getSnapshotDistinctCountForResource(resourceId);
	}

	public Long getScanCountForResource(int resourceId) {
		return scanDao.getCountForResource(resourceId);
	}

	public Date getLastVersionChange(int resourceId) {
		return scanDao.getLastVersionChange(resourceId);
	}

	public void saveScanFailureForResource(int resourceId) {
		Scan scan = new Scan();
		scan.setResourceId(resourceId);
		scan.setCreatedAt(new Date());
		scanDao.create(scan);
	}

	public void saveScanSuccessForResource(int resourceId, int snapshotId) {
		Scan scan = new Scan();
		scan.setResourceId(resourceId);
		scan.setSnapshotId(snapshotId);
		scan.setCreatedAt(new Date());
		scanDao.create(scan);
	}


}
