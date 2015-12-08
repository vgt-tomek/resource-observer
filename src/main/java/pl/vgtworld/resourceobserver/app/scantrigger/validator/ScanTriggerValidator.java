package pl.vgtworld.resourceobserver.app.scantrigger.validator;

import pl.vgtworld.resourceobserver.app.scantrigger.dto.ScanTriggerFormDto;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class ScanTriggerValidator {

	static final String ERROR_FORM_MISSING = "Form data is required.";
	static final String ERROR_RESOURCE_ID_REQUIRED = "Resource id is required.";
	static final String ERROR_RESOURCE_ID_NAN = "Resource id is not a number.";
	static final String ERROR_RESOURCE_NOT_EXIST = "Resource with provided id does not exist.";

	private ResourceService resourceService;

	private List<String> errors;

	public ScanTriggerValidator(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ValidationResult validate(ScanTriggerFormDto form) {
		errors = new ArrayList<>();
		if (form == null) {
			errors.add(ERROR_FORM_MISSING);
			return new ValidationResult(errors, null);
		}
		Integer validResourceId = validateResourceId(form.getResourceId());
		if (errors.isEmpty()) {
			return new ValidationResult(errors, validResourceId);
		} else {
			return new ValidationResult(errors, null);
		}
	}

	private Integer validateResourceId(String resourceId) {
		if (resourceId == null) {
			errors.add(ERROR_RESOURCE_ID_REQUIRED);
			return null;
		}
		try {
			int convertedResourceId = Integer.parseInt(resourceId);
			Resource resource = resourceService.findById(convertedResourceId);
			if (resource == null) {
				errors.add(ERROR_RESOURCE_NOT_EXIST);
				return null;
			}
			return convertedResourceId;
		} catch (NumberFormatException e) {
			errors.add(ERROR_RESOURCE_ID_NAN);
			return null;
		}
	}

}
