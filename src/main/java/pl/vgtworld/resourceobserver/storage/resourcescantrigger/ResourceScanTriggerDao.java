package pl.vgtworld.resourceobserver.storage.resourcescantrigger;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ResourceScanTriggerDao {

	@PersistenceContext
	private EntityManager em;

	public void create(ResourceScanTrigger scanTrigger) {
		em.persist(scanTrigger);
	}

	public ResourceScanTrigger findById(int id) {
		return em.find(ResourceScanTrigger.class, id);
	}

	public ResourceScanTrigger findActiveScanTriggerForResource(int resourceId) {
		Query query = em.createNamedQuery(ResourceScanTrigger.QUERY_FIND_ACTIVE_FOR_RESOURCE);
		query.setParameter("RESOURCE_ID", resourceId);
		return PersistenceUtil.getFirstResult(query);
	}

}
