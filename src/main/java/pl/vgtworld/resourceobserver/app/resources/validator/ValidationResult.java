package pl.vgtworld.resourceobserver.app.resources.validator;

import pl.vgtworld.resourceobserver.storage.resource.dto.NewResourceDto;

import java.util.List;

public class ValidationResult {

	private List<String> errors;

	private NewResourceDto createdResource;

	public ValidationResult(List<String> errors, NewResourceDto createdResource) {
		this.errors = errors;
		this.createdResource = createdResource;
	}

	public List<String> getErrors() {
		return errors;
	}

	public NewResourceDto getCreatedResource() {
		return createdResource;
	}

	public boolean isValid() {
		return errors.isEmpty() && createdResource != null;
	}

}
