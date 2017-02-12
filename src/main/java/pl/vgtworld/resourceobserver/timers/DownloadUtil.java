package pl.vgtworld.resourceobserver.timers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class DownloadUtil {

	private DownloadUtil() {
	}

	static byte[] downloadResource(String resourceUrl) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(resourceUrl);
		HttpResponse response = client.execute(request);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		response.getEntity().writeTo(output);
		return output.toByteArray();
	}

}
