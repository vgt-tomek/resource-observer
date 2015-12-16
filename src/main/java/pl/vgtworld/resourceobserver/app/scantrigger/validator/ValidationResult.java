package pl.vgtworld.resourceobserver.app.scantrigger.validator;

import pl.vgtworld.resourceobserver.core.validation.AbstractResult;

import java.util.List;

public class ValidationResult extends AbstractResult {

	private Integer resourceId;

	public ValidationResult(List<String> errors, Integer resourceId) {
		super(errors);
		this.resourceId = resourceId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public boolean isValid() {
		return super.isValid() && resourceId != null;
	}

}
