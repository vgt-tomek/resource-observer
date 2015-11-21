package pl.vgtworld.resourceobserver.storage.scan;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ScanDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(Scan scan) {
		em.persist(scan);
		return scan.getId();
	}

}
