package pl.vgtworld.resourceobserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.services.dto.mail.Attachment;
import pl.vgtworld.resourceobserver.services.dto.mail.Mail;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;

@Stateless
public class MailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

	@Resource(lookup = "java:jboss/mail/resource-observer-mail")
	private Session mailSession;

	public void sendMail(Mail mail) throws MessagingException {
		LOGGER.debug("Sending mail: {}", mail);
		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(mail.getSender()));
		for (String recipientAddress : mail.getRecipients()) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
		}
		message.setSubject(mail.getTitle());
		if (!mail.getAttachments().isEmpty()) {
			message.setContent(createMultipartMessageBody(mail.getBody(), mail.getAttachments()));
		} else {
			message.setText(mail.getBody());
		}
		message.setSentDate(new Date());
		Transport.send(message);
	}

	private static MimeMultipart createMultipartMessageBody(String body, List<Attachment> attachments) throws MessagingException {
		MimeMultipart multipart = new MimeMultipart();

		BodyPart content = new MimeBodyPart();
		content.setText(body);
		multipart.addBodyPart(content);
		for (Attachment attachment : attachments) {
			BodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.setFileName(attachment.getFilename());
			if ("text/plain".equals(attachment.getMediaType())) {
				attachmentBodyPart.setContent(new String(attachment.getAttachmentBody()), attachment.getMediaType());
			} else {
				attachmentBodyPart.setContent(attachment.getAttachmentBody(), attachment.getMediaType());
			}
			multipart.addBodyPart(attachmentBodyPart);
		}
		return multipart;
	}

}
