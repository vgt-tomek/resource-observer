package pl.vgtworld.resourceobserver.storage.resourcescantrigger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ResourceScanTriggerDao {

	@PersistenceContext
	private EntityManager em;

	public void create(ResourceScanTrigger scanTrigger) {
		em.persist(scanTrigger);
	}

}
