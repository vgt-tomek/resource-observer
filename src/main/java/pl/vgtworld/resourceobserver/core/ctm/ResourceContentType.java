package pl.vgtworld.resourceobserver.core.ctm;

public class ResourceContentType {

	private byte[] fingerprint;

	private String name;

	private String extension;

	private boolean binary;

	public ResourceContentType(byte[] fingerprint, String name, String extension, boolean binary) {
		this.fingerprint = fingerprint;
		this.name = name;
		this.extension = extension;
		this.binary = binary;
	}

	public byte[] getFingerprint() {
		return fingerprint;
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	public boolean isBinary() {
		return binary;
	}

	public String createFilename(String baseName) {
		return baseName + (extension != null ? "." + extension : "");
	}

}
