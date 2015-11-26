package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;
import pl.vgtworld.resourceobserver.storage.snapshot.SnapshotDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SnapshotService {

	@EJB
	private SnapshotDao snapshotDao;

	public Snapshot findById(int id) {
		return snapshotDao.findById(id);
	}

	public int findIdForSnapshot(String hash, byte[] resource) {
		Snapshot snapshot = snapshotDao.findByHash(hash);
		if (snapshot != null) {
			return snapshot.getId();
		}
		snapshot = new Snapshot();
		snapshot.setHash(hash);
		snapshot.setResource(resource);
		return snapshotDao.create(snapshot);
	}

}
