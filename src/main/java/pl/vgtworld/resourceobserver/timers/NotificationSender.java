package pl.vgtworld.resourceobserver.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.services.NotificationService;
import pl.vgtworld.resourceobserver.storage.notification.resourcechange.NotificationResourceChange;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class NotificationSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSender.class);

	@EJB
	private NotificationService notificationService;

	@Schedule(second = "30", minute = "*", hour = "*", persistent = false)
	public void sendNotifications() {
		LOGGER.debug("Send notifications event triggered");

		List<NotificationResourceChange> notifications = notificationService.findNotSentNotificationsForResourceChange();
		if (notifications.isEmpty()) {
			LOGGER.debug("No unsent notifications found. Aborting.");
			return;
		}
		LOGGER.info("New unsent notifications found: {}", notifications.size());
		//TODO Send notifications.
	}
}
