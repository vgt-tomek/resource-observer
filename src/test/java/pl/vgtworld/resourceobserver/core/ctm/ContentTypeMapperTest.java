package pl.vgtworld.resourceobserver.core.ctm;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentTypeMapperTest {

	@Test
	public void shouldRecognizeJpegImage() throws IOException {
		byte[] jpgBytes = loadResource("/file-examples/test.jpg");
		ContentTypeMapper mapper = new ContentTypeMapper();

		ResourceContentType result = mapper.findContentTypeForResource(jpgBytes);

		assertThat(result.getName()).isEqualTo("image/jpeg");
		assertThat(result.getExtension()).isEqualTo("jpg");
	}

	@Test
	public void shouldRecognizePngImage() throws IOException {
		byte[] jpgBytes = loadResource("/file-examples/test.png");
		ContentTypeMapper mapper = new ContentTypeMapper();

		ResourceContentType result = mapper.findContentTypeForResource(jpgBytes);

		assertThat(result.getName()).isEqualTo("image/png");
		assertThat(result.getExtension()).isEqualTo("png");
	}

	@Test
	public void shouldRecognizeXmlImage() throws IOException {
		byte[] jpgBytes = loadResource("/file-examples/test.xml");
		ContentTypeMapper mapper = new ContentTypeMapper();

		ResourceContentType result = mapper.findContentTypeForResource(jpgBytes);

		assertThat(result.getName()).isEqualTo("application/xml");
		assertThat(result.getExtension()).isEqualTo("xml");
	}

	@Test
	public void shouldRecognizeTextFile() throws IOException {
		byte[] jpgBytes = loadResource("/file-examples/test.txt");
		ContentTypeMapper mapper = new ContentTypeMapper();

		ResourceContentType result = mapper.findContentTypeForResource(jpgBytes);

		assertThat(result.getName()).isEqualTo("text/plain");
		assertThat(result.getExtension()).isEqualTo("txt");
	}

	@Test
	public void shouldRecognizeUnsupportedFileAsOctetStream() throws IOException {
		byte[] jpgBytes = loadResource("/file-examples/test.odt");
		ContentTypeMapper mapper = new ContentTypeMapper();

		ResourceContentType result = mapper.findContentTypeForResource(jpgBytes);

		assertThat(result.getName()).isEqualTo("application/octet-stream");
		assertThat(result.getExtension()).isNull();
	}

	private byte[] loadResource(String resource) throws IOException {
		InputStream stream = getClass().getResourceAsStream(resource);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		stream.close();
		output.flush();
		output.close();
		return output.toByteArray();
	}

}