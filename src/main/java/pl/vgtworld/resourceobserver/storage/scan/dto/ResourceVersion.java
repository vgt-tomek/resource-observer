package pl.vgtworld.resourceobserver.storage.scan.dto;

import java.util.Date;

public class ResourceVersion {

	private Integer id;

	private Date firstOccurrence;

	private Date lastOccurrence;

	private Long count;

	public ResourceVersion(Integer id, Date firstOccurrence, Date lastOccurrence, Long count) {
		this.id = id;
		this.firstOccurrence = firstOccurrence;
		this.lastOccurrence = lastOccurrence;
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
