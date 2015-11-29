package pl.vgtworld.resourceobserver.services.dto.mail;

public class Attachment {

	private String filename;

	private String mediaType;

	private byte[] attachmentBody;

	public Attachment(String filename, String mediaType, byte[] attachmentBody) {
		this.filename = filename;
		this.mediaType = mediaType;
		this.attachmentBody = attachmentBody;
	}

	public String getFilename() {
		return filename;
	}

	public String getMediaType() {
		return mediaType;
	}

	public byte[] getAttachmentBody() {
		return attachmentBody;
	}

	@Override
	public String toString() {
		return "Attachment{" +
			  "filename='" + filename + '\'' +
			  ", mediaType='" + mediaType + '\'' +
			  ", size=" + attachmentBody.length +
			  '}';
	}
}
