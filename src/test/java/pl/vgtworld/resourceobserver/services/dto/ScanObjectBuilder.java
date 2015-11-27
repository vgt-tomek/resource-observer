package pl.vgtworld.resourceobserver.services.dto;

import java.util.Date;

public class ScanObjectBuilder {

	private Date createdAt;

	private ResourceVersion resourceVersion;

	public ScanObjectBuilder createValidInstance() {
		createdAt = new Date();
		resourceVersion = new ResourceVersionObjectBuilder().createValidInstance().build();
		return this;
	}

	public ScanObjectBuilder createInstanceWithoutVersion() {
		createdAt = new Date();
		resourceVersion = null;
		return this;
	}

	public ScanObjectBuilder withCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public ScanObjectBuilder withResourceVersion(ResourceVersion resourceVersion) {
		this.resourceVersion = resourceVersion;
		return this;
	}

	public Scan build() {
		Scan scan = new Scan();
		scan.setCreatedAt(createdAt);
		scan.setVersion(resourceVersion);
		return scan;
	}

}
