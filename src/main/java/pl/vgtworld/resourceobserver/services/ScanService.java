package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.storage.scan.Scan;
import pl.vgtworld.resourceobserver.storage.scan.ScanDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

}
