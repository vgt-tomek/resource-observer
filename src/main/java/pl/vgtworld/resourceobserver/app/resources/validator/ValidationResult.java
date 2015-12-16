package pl.vgtworld.resourceobserver.app.resources.validator;

import pl.vgtworld.resourceobserver.core.validation.AbstractResult;
import pl.vgtworld.resourceobserver.services.dto.NewResourceDto;

import java.util.List;

public class ValidationResult extends AbstractResult {

	private NewResourceDto createdResource;

	public ValidationResult(List<String> errors, NewResourceDto createdResource) {
		super(errors);
		this.createdResource = createdResource;
	}

	public NewResourceDto getCreatedResource() {
		return createdResource;
	}

	public boolean isValid() {
		return super.isValid() && createdResource != null;
	}

}
