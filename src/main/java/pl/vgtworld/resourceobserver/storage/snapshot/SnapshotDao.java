package pl.vgtworld.resourceobserver.storage.snapshot;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SnapshotDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(Snapshot snapshot) {
		em.persist(snapshot);
		return snapshot.getId();
	}

	public Snapshot findById(int id) {
		return em.find(Snapshot.class, id);
	}

	public Snapshot findByHash(String hash) {
		Query query = em.createNamedQuery(Snapshot.QUERY_FIND_BY_HASH);
		query.setParameter("HASH", hash);
		return PersistenceUtil.getSingleResult(query);
	}

}
