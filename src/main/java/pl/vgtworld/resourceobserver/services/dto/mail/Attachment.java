package pl.vgtworld.resourceobserver.services.dto.mail;

import java.util.Arrays;

public class Attachment {

	private String filename;

	private String mediaType;

	private byte[] attachment;

	public Attachment(String filename, String mediaType, byte[] attachment) {
		this.filename = filename;
		this.mediaType = mediaType;
		this.attachment = attachment;
	}

	public String getFilename() {
		return filename;
	}

	public String getMediaType() {
		return mediaType;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	@Override
	public String toString() {
		return "Attachment{" +
			  "filename='" + filename + '\'' +
			  ", mediaType='" + mediaType + '\'' +
			  ", size=" + attachment.length +
			  '}';
	}
}
