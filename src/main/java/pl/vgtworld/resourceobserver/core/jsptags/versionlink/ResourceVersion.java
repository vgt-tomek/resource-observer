package pl.vgtworld.resourceobserver.core.jsptags.versionlink;

import java.awt.Color;
import java.util.Date;

public class ResourceVersion {

	private Integer versionId;

	private Integer snapshotId;

	private Color backgroundColor;

	private Date firstOccurrence;

	private Date lastOccurrence;

	private Long count;

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Integer getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Date getFirstOccurrence() {
		return firstOccurrence;
	}

	public void setFirstOccurrence(Date firstOccurrence) {
		this.firstOccurrence = firstOccurrence;
	}

	public Date getLastOccurrence() {
		return lastOccurrence;
	}

	public void setLastOccurrence(Date lastOccurrence) {
		this.lastOccurrence = lastOccurrence;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
