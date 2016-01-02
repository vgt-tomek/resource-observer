package pl.vgtworld.resourceobserver.services.dto;

import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;

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
