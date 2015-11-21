package pl.vgtworld.resourceobserver.app.resources.models.list;

import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class ListModel {

	private List<Resource> resources;

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
