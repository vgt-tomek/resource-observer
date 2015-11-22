package pl.vgtworld.resourceobserver.storage.scan;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;
import pl.vgtworld.resourceobserver.storage.scan.dto.ResourceVersion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ScanDao {

	private static final String RESOURCE_ID_QUERY_PARAM = "RESOURCE_ID";

	@PersistenceContext
	private EntityManager em;

	public Integer create(Scan scan) {
		em.persist(scan);
		return scan.getId();
	}

	public List<Scan> findNewestForResource(int resourceId, Integer count) {
		Query query = em.createNamedQuery(Scan.QUERY_FIND_NEWEST_FOR_RESOURCE);
		query.setParameter(RESOURCE_ID_QUERY_PARAM, resourceId);
		if (count != null) {
			query.setMaxResults(count);
		}
		return PersistenceUtil.getResultList(query);
	}

	public List<ResourceVersion> findVersionsForResource(int resourceId) {
		Query query = em.createNamedQuery(Scan.QUERY_FIND_VERSIONS_FOR_RESOURCE);
		query.setParameter(RESOURCE_ID_QUERY_PARAM, resourceId);
		return PersistenceUtil.getResultList(query);
	}

	public Long getCountForResource(int resourceId) {
		Query query = em.createNamedQuery(Scan.QUERY_GET_COUNT_FOR_RESOURCE);
		query.setParameter(RESOURCE_ID_QUERY_PARAM, resourceId);
		return PersistenceUtil.getSingleResult(query);
	}

	public Long getSnapshotDistinctCountForResource(int resourceId) {
		Query query = em.createNamedQuery(Scan.QUERY_GET_UNIQUE_SNAPSHOT_COUNT_FOR_RESOURCE);
		query.setParameter(RESOURCE_ID_QUERY_PARAM, resourceId);
		return PersistenceUtil.getSingleResult(query);
	}

}
