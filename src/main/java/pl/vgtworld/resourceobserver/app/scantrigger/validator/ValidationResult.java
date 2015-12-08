package pl.vgtworld.resourceobserver.app.scantrigger.validator;

import java.util.List;

public class ValidationResult {

	private List<String> errors;

	private Integer resourceId;

	public ValidationResult(List<String> errors, Integer resourceId) {
		this.errors = errors;
		this.resourceId = resourceId;
	}

	public List<String> getErrors() {
		return errors;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public boolean isValid() {
		return errors != null && errors.isEmpty() && resourceId != null;
	}

}
