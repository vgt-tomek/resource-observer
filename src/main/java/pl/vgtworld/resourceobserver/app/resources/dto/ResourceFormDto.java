package pl.vgtworld.resourceobserver.app.resources.dto;

import javax.ws.rs.FormParam;

public class ResourceFormDto {

	@FormParam("name")
	private String name;

	@FormParam("url")
	private String url;

	@FormParam("checkInterval")
	private String checkInterval;

	@FormParam("observers")
	private String observers;

	@FormParam("active")
	private String active;

	@FormParam("resourceFormSubmit")
	private String resourceFormSubmit;

	@FormParam("resourceFormCancel")
	private String resourceFormCancel;

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

	public String getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(String checkInterval) {
		this.checkInterval = checkInterval;
	}

	public String getObservers() {
		return observers;
	}

	public void setObservers(String observers) {
		this.observers = observers;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getResourceFormSubmit() {
		return resourceFormSubmit;
	}

	public void setResourceFormSubmit(String resourceFormSubmit) {
		this.resourceFormSubmit = resourceFormSubmit;
	}

	public String getResourceFormCancel() {
		return resourceFormCancel;
	}

	public void setResourceFormCancel(String resourceFormCancel) {
		this.resourceFormCancel = resourceFormCancel;
	}

	@Override
	public String toString() {
		return "ResourceFormDto{" +
			  "name='" + name + '\'' +
			  ", url='" + url + '\'' +
			  ", checkInterval='" + checkInterval + '\'' +
			  ", observers='" + observers + '\'' +
			  ", active=" + active +
			  ", resourceFormSubmit='" + resourceFormSubmit + '\'' +
			  ", resourceFormCancel='" + resourceFormCancel + '\'' +
			  '}';
	}

}
