package pl.vgtworld.resourceobserver.storage.snapshot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SnapshotDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(Snapshot snapshot) {
		em.persist(snapshot);
		return snapshot.getId();
	}

}
