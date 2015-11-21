package pl.vgtworld.app.resources.models.form;

import pl.vgtworld.app.resources.ResourceFormDto;

import java.util.List;

public class FormModel {

	private List<String> errors;

	private ResourceFormDto resource;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ResourceFormDto getResource() {
		return resource;
	}

	public void setResource(ResourceFormDto resource) {
		this.resource = resource;
	}

}
