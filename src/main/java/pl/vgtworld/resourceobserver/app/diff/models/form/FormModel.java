package pl.vgtworld.resourceobserver.app.diff.models.form;

import pl.vgtworld.resourceobserver.core.diffutil.dto.DiffLine;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class FormModel {

	private List<String> errors;

	private Resource resource;

	private List<ResourceVersion> versions;

	private List<DiffLine> diffLines;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

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

	public List<DiffLine> getDiffLines() {
		return diffLines;
	}

	public void setDiffLines(List<DiffLine> diffLines) {
		this.diffLines = diffLines;
	}

}