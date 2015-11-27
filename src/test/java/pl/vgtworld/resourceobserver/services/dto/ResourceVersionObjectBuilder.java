package pl.vgtworld.resourceobserver.services.dto;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

public class ResourceVersionObjectBuilder {

	private Color backgroundColor;

	private Date firstOccurence;

	private Date lastOccurence;

	private Long count;

	private Integer versionId;

	private Integer snapshotId;

	public ResourceVersionObjectBuilder createValidInstance() {
		backgroundColor = Color.RED;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -2);
		lastOccurence = calendar.getTime();
		calendar.add(Calendar.HOUR, -2);
		firstOccurence = calendar.getTime();
		count = 42L;
		versionId = 8;
		snapshotId = 21;
		return this;
	}

	public ResourceVersionObjectBuilder withBackgroundColor(Color color) {
		backgroundColor = color;
		return this;
	}

	public ResourceVersionObjectBuilder withFirstOccurence(Date firstOccurence) {
		this.firstOccurence = firstOccurence;
		return this;
	}

	public ResourceVersionObjectBuilder withLastOccurence(Date lastOccurence) {
		this.lastOccurence = lastOccurence;
		return this;
	}

	public ResourceVersionObjectBuilder withCount(Long count) {
		this.count = count;
		return this;
	}

	public ResourceVersionObjectBuilder withVersionId(Integer versionId) {
		this.versionId = versionId;
		return this;
	}

	public ResourceVersionObjectBuilder withSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
		return this;
	}

	public ResourceVersion build() {
		ResourceVersion version = new ResourceVersion();
		version.setBackgroundColor(backgroundColor);
		version.setFirstOccurrence(firstOccurence);
		version.setLastOccurrence(lastOccurence);
		version.setCount(count);
		version.setVersionId(versionId);
		version.setSnapshotId(snapshotId);
		return version;
	}

}
