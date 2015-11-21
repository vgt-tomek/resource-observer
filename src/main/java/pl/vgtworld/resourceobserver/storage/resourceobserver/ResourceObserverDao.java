package pl.vgtworld.resourceobserver.storage.resourceobserver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ResourceObserverDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(ResourceObserver observer) {
		em.persist(observer);
		return observer.getId();
	}

}
