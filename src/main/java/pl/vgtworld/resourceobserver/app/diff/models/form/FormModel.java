package pl.vgtworld.resourceobserver.app.diff.models.form;

import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class FormModel {

	private Resource resource;

	private List<ResourceVersion> versions;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<ResourceVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<ResourceVersion> versions) {
		this.versions = versions;
	}

}
