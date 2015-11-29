package pl.vgtworld.resourceobserver.storage.notification.resourcechange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.core.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class NotificationResourceChangeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationResourceChangeDao.class);

	@PersistenceContext
	private EntityManager em;

	public void createNotification(NotificationResourceChange notification) {
		em.persist(notification);
	}

	public NotificationResourceChange findById(int id) {
		return em.find(NotificationResourceChange.class, id);
	}

	public List<NotificationResourceChange> findNotSent() {
		Query query = em.createNamedQuery(NotificationResourceChange.QUERY_FIND_NOT_SENT);
		return PersistenceUtil.getResultList(query);
	}

	public void updateStatus(int notificationId, Status newStatus) {
		NotificationResourceChange notification = findById(notificationId);
		if (notification == null) {
			LOGGER.error("Unable to update notification status. Notification #{} does not exist.", notificationId);
			return;
		}
		notification.setStatus(newStatus);
		notification.setProcessedAt(new Date());
	}

}
