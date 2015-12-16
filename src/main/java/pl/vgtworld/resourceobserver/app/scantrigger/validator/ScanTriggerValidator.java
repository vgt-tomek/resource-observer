package pl.vgtworld.resourceobserver.app.scantrigger.validator;

import pl.vgtworld.resourceobserver.app.scantrigger.dto.ScanTriggerFormDto;
import pl.vgtworld.resourceobserver.core.validation.AbstractResult;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class ScanTriggerValidator {

	public static class Result extends AbstractResult {

		private Integer resourceId;

		public Result(List<String> errors, Integer resourceId) {
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

	static class Errors {
		static final String FORM_MISSING = "Form data is required.";
		static final String RESOURCE_ID_REQUIRED = "Resource id is required.";
		static final String RESOURCE_ID_NAN = "Resource id is not a number.";
		static final String RESOURCE_NOT_EXIST = "Resource with provided id does not exist.";

		private Errors() {
		}

	}
	private ResourceService resourceService;

	private List<String> errors;

	public ScanTriggerValidator(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public Result validate(ScanTriggerFormDto form) {
		errors = new ArrayList<>();
		if (form == null) {
			errors.add(Errors.FORM_MISSING);
			return new Result(errors, null);
		}
		Integer validResourceId = validateResourceId(form.getResourceId());
		if (errors.isEmpty()) {
			return new Result(errors, validResourceId);
		} else {
			return new Result(errors, null);
		}
	}

	private Integer validateResourceId(String resourceId) {
		if (resourceId == null) {
			errors.add(Errors.RESOURCE_ID_REQUIRED);
			return null;
		}
		try {
			int convertedResourceId = Integer.parseInt(resourceId);
			Resource resource = resourceService.findById(convertedResourceId);
			if (resource == null) {
				errors.add(Errors.RESOURCE_NOT_EXIST);
				return null;
			}
			return convertedResourceId;
		} catch (NumberFormatException e) {
			errors.add(Errors.RESOURCE_ID_NAN);
			return null;
		}
	}

}
