package pl.vgtworld.resourceobserver.app.resources.models.list;

import java.util.Date;

public class ResourceModel {

	private int id;

	private String name;

	private String url;

	private boolean active;

	private int checkInterval;

	private Date lastCheckAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(int checkInterval) {
		this.checkInterval = checkInterval;
	}

	public Date getLastCheckAt() {
		return lastCheckAt;
	}

	public void setLastCheckAt(Date lastCheckAt) {
		this.lastCheckAt = lastCheckAt;
	}

}
