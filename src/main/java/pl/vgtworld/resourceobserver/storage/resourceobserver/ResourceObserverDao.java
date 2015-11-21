package pl.vgtworld.resourceobserver.storage.resourceobserver;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ResourceObserverDao {

	@PersistenceContext
	private EntityManager em;

	public Integer create(ResourceObserver observer) {
		em.persist(observer);
		return observer.getId();
	}

	public List<ResourceObserver> findAllForResource(int resourceId) {
		Query query = em.createNamedQuery(ResourceObserver.QUERY_FIND_ALL_FOR_RESOURCE);
		query.setParameter("RESOURCE_ID", resourceId);
		return PersistenceUtil.getResultList(query);
	}

	public void removeObserversForResource(int resourceId) {
		Query query = em.createNamedQuery(ResourceObserver.QUERY_REMOVE_ALL_FOR_RESOURCE);
		query.setParameter("RESOURCE_ID", resourceId);
		query.executeUpdate();
	}

}
