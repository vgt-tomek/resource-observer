package pl.vgtworld.resourceobserver.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class NotificationSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSender.class);

	@Schedule(second = "30", minute = "*", hour = "*", persistent = false)
	public void sendNotifications() {
		LOGGER.debug("Send notifications event triggered");
	}
}
