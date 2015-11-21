package pl.vgtworld.app.resources;

public class ResourceBuilder {

	private String name;

	private String url;

	private String active;

	private String checkInterval;

	private String observers;

	public ResourceBuilder createValidResource() {
		name = "sample name";
		url = "http://www.sample-url.com/resource";
		active = "on";
		checkInterval = "60";
		observers = "address1@example.com\r\naddress2@example.com";
		return this;
	}

	public ResourceBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ResourceBuilder withUrl(String url) {
		this.url = url;
		return this;
	}

	public ResourceBuilder withActive(String active) {
		this.active = active;
		return this;
	}

	public ResourceBuilder withCheckInterval(String checkInterval) {
		this.checkInterval = checkInterval;
		return this;
	}

	public ResourceBuilder withRawObservers(String observers) {
		this.observers = observers;
		return this;
	}

	public ResourceBuilder withObservers(String... observers) {
		StringBuilder mergedObservers = new StringBuilder();
		for (String observer : observers) {
			mergedObservers.append("\n").append(observer);
		}
		this.observers = mergedObservers.toString().trim();
		return this;
	}

	public ResourceFormDto build() {
		ResourceFormDto result = new ResourceFormDto();
		result.setName(name);
		result.setUrl(url);
		result.setActive(active);
		result.setCheckInterval(checkInterval);
		result.setObservers(observers);
		return result;
	}
}
