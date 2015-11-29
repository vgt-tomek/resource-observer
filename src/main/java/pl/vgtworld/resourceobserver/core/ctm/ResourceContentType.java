package pl.vgtworld.resourceobserver.core.ctm;

public class ResourceContentType {

	private byte[] fingerprint;

	private String name;

	private String extension;

	public ResourceContentType(byte[] fingerprint, String name, String extension) {
		this.fingerprint = fingerprint;
		this.name = name;
		this.extension = extension;
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

}
