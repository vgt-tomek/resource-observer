package pl.vgtworld.storage.resourceobserver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ResourceObserverDao {

	@PersistenceContext
	private EntityManager em;

}
