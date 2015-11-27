package pl.vgtworld.resourceobserver.services.dto;

import java.util.Calendar;
import java.util.Date;

public class ScanObjectBuilder {

	private Date createdAt;

	private ResourceVersion resourceVersion;

	public ScanObjectBuilder createValidInstance() {
		createdAt = new Date();
		resourceVersion = new ResourceVersionObjectBuilder().createValidInstance().build();
		return this;
	}

	public ScanObjectBuilder createValidInstance(int snapshotId, int year, int month, int day) {
		withCreatedAt(year, month, day);
		resourceVersion = new ResourceVersionObjectBuilder()
			  .createValidInstance()
			  .withSnapshotId(snapshotId)
			  .build();
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

	public ScanObjectBuilder withCreatedAt(int year, int month, int day) {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR, year);
		instance.set(Calendar.MONTH, month);
		instance.set(Calendar.DAY_OF_MONTH, day);
		this.createdAt = instance.getTime();
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
