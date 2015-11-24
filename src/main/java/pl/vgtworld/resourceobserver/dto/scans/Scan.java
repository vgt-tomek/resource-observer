package pl.vgtworld.resourceobserver.dto.scans;

import pl.vgtworld.resourceobserver.app.resources.models.details.ResourceVersion;

import java.util.Date;

public class Scan {

	private Date createdAt;

	private ResourceVersion version;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ResourceVersion getVersion() {
		return version;
	}

	public void setVersion(ResourceVersion version) {
		this.version = version;
	}

}
