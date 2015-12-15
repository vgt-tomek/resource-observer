package pl.vgtworld.resourceobserver.core.ctm;

import java.util.Arrays;
import java.util.List;

public class ContentTypeMapper {

	private static List<ResourceContentType> contentTypes = ContentTypeFactory.createContentTypes();

	public ResourceContentType findContentTypeForResource(byte[] resource) {
		for (ResourceContentType contentType : contentTypes) {
			byte[] fingerprint = contentType.getFingerprint();
			byte[] resourcePrefix = Arrays.copyOfRange(resource, 0, fingerprint.length);
			if (Arrays.equals(fingerprint, resourcePrefix)) {
				return contentType;
			}
		}
		if (isTextFile(resource)) {
			return getTextFileContentType();
		}
		return getDefaultContentType();
	}

	private boolean isTextFile(byte[] resource) {
		return Arrays.binarySearch(resource, (byte) 0x00) == -1;
	}

	private ResourceContentType getTextFileContentType() {
		return new ResourceContentType(null, "text/plain", "txt", false);
	}

	private ResourceContentType getDefaultContentType() {
		return new ResourceContentType(null, "application/octet-stream", null, true);
	}

}
