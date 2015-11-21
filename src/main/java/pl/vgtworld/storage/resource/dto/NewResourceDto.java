package pl.vgtworld.storage.resource.dto;

import java.util.List;

public class NewResourceDto {

	private String name;

	private String url;

	private Boolean active;

	private Integer checkInterval;

	private List<String> observers;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(Integer checkInterval) {
		this.checkInterval = checkInterval;
	}

	public List<String> getObservers() {
		return observers;
	}

	public void setObservers(List<String> observers) {
		this.observers = observers;
	}

}
