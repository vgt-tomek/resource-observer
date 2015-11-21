package pl.vgtworld.resourceobserver.storage.scan;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ScanDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(Scan scan) {
		em.persist(scan);
		return scan.getId();
	}

	public List<Scan> findNewestForResource(int resourceId, int count) {
		Query query = em.createNamedQuery(Scan.QUERY_FIND_NEWEST_FOR_RESOURCE);
		query.setParameter("RESOURCE_ID", resourceId);
		query.setMaxResults(count);
		return PersistenceUtil.getResultList(query);
	}

}
