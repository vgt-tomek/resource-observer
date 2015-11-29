package pl.vgtworld.resourceobserver.timers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.core.PropertyNames;
import pl.vgtworld.resourceobserver.services.MailService;
import pl.vgtworld.resourceobserver.services.NotificationService;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.SnapshotService;
import pl.vgtworld.resourceobserver.services.dto.mail.Attachment;
import pl.vgtworld.resourceobserver.services.dto.mail.Mail;
import pl.vgtworld.resourceobserver.storage.notification.resourcechange.NotificationResourceChange;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.resourceobserver.ResourceObserver;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Singleton
public class NotificationSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationSender.class);

	@EJB
	private NotificationService notificationService;

	@EJB
	private ResourceService resourceService;

	@EJB
	private SnapshotService snapshotService;

	@EJB
	private MailService mailService;

	@Schedule(second = "30", minute = "*", hour = "*", persistent = false)
	public void sendNotifications() {
		LOGGER.debug("Send notifications event triggered");

		List<NotificationResourceChange> notifications = notificationService.findNotSentNotificationsForResourceChange();
		if (notifications.isEmpty()) {
			LOGGER.debug("No unsent notifications found. Aborting.");
			return;
		}
		LOGGER.info("New unsent notifications found: {}", notifications.size());
		notifications.forEach(this::sentEmailForNotification);
	}

	private void sentEmailForNotification(NotificationResourceChange notification) {
		Mail mail = createMailDtoForNotification(notification);
		if (mail != null) {
			LOGGER.info("Sending resource change notification for resource #{}", notification.getResourceId());
			try {
				mailService.sendMail(mail);
				notificationService.markNotificationAsSent(notification.getId());
			} catch (MessagingException e) {
				LOGGER.error("Unable to send e-mail. Exception occurred.", e);
			}
		}
	}

	private Mail createMailDtoForNotification(NotificationResourceChange notification) {
		String sender = System.getProperty(PropertyNames.MAIL_SESSION_SENDER_ADDRESS, null);
		if (sender == null) {
			LOGGER.error("Sender address not found. Unable to send e-mail notification.");
			LOGGER.debug("Property {} is missing.", PropertyNames.MAIL_SESSION_SENDER_ADDRESS);
			return null;
		}
		Resource resource = resourceService.findById(notification.getResourceId());
		List<ResourceObserver> resourceObservers = resourceService.findAllObserversForResource(notification.getResourceId());
		if (resourceObservers.isEmpty()) {
			LOGGER.debug("No observers found for resource.");
			notificationService.markNotificationAsNoRecipients(notification.getId());
			return null;
		}
		Snapshot oldSnapshot = snapshotService.findById(notification.getSnapshotOldId());
		Snapshot newSnapshot = snapshotService.findById(notification.getSnapshotNewId());
		String body = createEmailBody(notification, resource);
		List<String> recipients = resourceObservers.stream().map(ResourceObserver::getEmail).collect(Collectors.toList());
		List<Attachment> attachments = new ArrayList<>();
		attachments.add(new Attachment(
			  "resource-old",
			  "application/octet-stream",
			  oldSnapshot.getResource()
		));
		attachments.add(new Attachment(
			  "resource-new",
			  "application/octet-stream",
			  newSnapshot.getResource()
		));
		return new Mail(
			  sender,
			  "Resource observer notification - " + resource.getName(),
			  body,
			  recipients,
			  attachments
		);
	}

	private String createEmailBody(NotificationResourceChange notification, Resource resource) {
		String bodyTemplate = loadEmailBodyTemplate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return bodyTemplate.replace("{resource-name}", resource.getName()).replace("{change-timestamp}", sdf.format(notification.getCreatedAt()));
	}

	private String loadEmailBodyTemplate() {
		InputStream stream = getClass().getResourceAsStream("/mail-templates/resource-change.txt");
		Scanner scanner = new Scanner(stream);
		StringBuilder template = new StringBuilder();
		while (scanner.hasNextLine()) {
			template.append(scanner.nextLine()).append("\n");
		}
		scanner.close();
		return template.toString();
	}
}
