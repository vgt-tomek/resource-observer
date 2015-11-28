package pl.vgtworld.resourceobserver.storage.notification.resourcechange;

import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class NotificationResourceChangeDao {

	@PersistenceContext
	private EntityManager em;

	public void createNotification(NotificationResourceChange notification) {
		em.persist(notification);
	}

	public List<NotificationResourceChange> findNotSent() {
		Query query = em.createNamedQuery(NotificationResourceChange.QUERY_FIND_NOT_SENT);
		return PersistenceUtil.getResultList(query);
	}

}
