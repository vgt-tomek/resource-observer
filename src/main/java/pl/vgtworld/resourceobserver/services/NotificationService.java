package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.storage.notification.resourcechange.NotificationResourceChange;
import pl.vgtworld.resourceobserver.storage.notification.resourcechange.NotificationResourceChangeDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class NotificationService {

	@EJB
	private NotificationResourceChangeDao notificationResourceChangeDao;

	public void createNewNotificationForResourceChange(int resourceId, int snapshotOldId, int snapshotNewId) {
		NotificationResourceChange entity = new NotificationResourceChange();
		entity.setResourceId(resourceId);
		entity.setSnapshotOldId(snapshotOldId);
		entity.setSnapshotNewId(snapshotNewId);
		entity.setCreatedAt(new Date());
		notificationResourceChangeDao.createNotification(entity);
	}

	public List<NotificationResourceChange> findNotSentNotificationsForResourceChange() {
		return notificationResourceChangeDao.findNotSent();
	}

	public void markNotificationAsSent(int notificationId) {
		notificationResourceChangeDao.updateSentAt(notificationId, new Date());
	}

}
