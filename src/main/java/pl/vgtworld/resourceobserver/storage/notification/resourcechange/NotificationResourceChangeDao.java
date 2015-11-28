package pl.vgtworld.resourceobserver.storage.notification.resourcechange;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NotificationResourceChangeDao {

	@PersistenceContext
	private EntityManager em;

	public void createNotification(NotificationResourceChange notification) {
		em.persist(notification);
	}

}
