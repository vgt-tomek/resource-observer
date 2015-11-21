package pl.vgtworld.resourceobserver.timers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

final class DownloadUtil {

	private static final int BUFFER_SIZE = 16384;

	private DownloadUtil() {
	}

	static byte[] downloadResource(String resourceUrl) throws IOException {
		URL url = new URL(resourceUrl);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try (
			  InputStream stream = url.openStream()
		) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		}
		return output.toByteArray();
	}

}
