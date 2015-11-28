package pl.vgtworld.resourceobserver.services.dto.mail;

import java.util.List;

public class Mail {

	private String sender;

	private String title;

	private String body;

	private List<String> recipients;

	private List<Attachment> attachments;

	public Mail(String sender, String title, String body, List<String> recipients, List<Attachment> attachments) {
		this.sender = sender;
		this.title = title;
		this.body = body;
		this.recipients = recipients;
		this.attachments = attachments;
	}

	public String getSender() {
		return sender;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	@Override
	public String toString() {
		return "Mail{" +
			  "sender='" + sender + '\'' +
			  ", title='" + title + '\'' +
			  ", body='" + body + '\'' +
			  ", recipients=" + recipients +
			  ", attachments=" + attachments +
			  '}';
	}
}
