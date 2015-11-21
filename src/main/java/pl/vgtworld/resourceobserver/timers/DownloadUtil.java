package pl.vgtworld.resourceobserver.timers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

final class DownloadUtil {

	private DownloadUtil() {
	}

	static String downloadResource(String resourceUrl) throws IOException {
		StringBuilder output = new StringBuilder();
		URL url = new URL(resourceUrl);
		try (
			  InputStream stream = url.openStream();
			  Scanner scanner = new Scanner(stream, "UTF-8")
		) {
			while (scanner.hasNextLine()) {
				output.append(scanner.nextLine());
			}
		}
		return output.toString();
	}

}
