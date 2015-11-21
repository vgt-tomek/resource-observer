package pl.vgtworld.resourceobserver.app.resources.validator;

import pl.vgtworld.resourceobserver.app.resources.ResourceFormDto;
import pl.vgtworld.resourceobserver.services.dto.NewResourceDto;

import java.util.ArrayList;
import java.util.List;

public class ResourceValidator {

	static class Errors {
		static final String NAME_REQUIRED = "Name is required.";
		static final String NAME_TOO_LONG = "Name maximum length is " + NAME_MAX_LENGTH + " characters.";
		static final String URL_REQUIRED = "Url is required.";
		static final String URL_TOO_LONG = "Url maximum length is " + URL_MAX_LENGTH + " characters.";
		static final String CHECK_INTERVAL_REQUIRED = "Check interval is required.";
		static final String CHECK_INTERVAL_NAN = "Check interval must be an integer.";
		static final String CHECK_INTERVAL_GT_ZERO = "Check interval must be greater than zero.";
	}

	private static final int NAME_MAX_LENGTH = 100;
	private static final int URL_MAX_LENGTH = 250;

	private List<String> errors;

	public ValidationResult validate(ResourceFormDto resource) {
		errors = new ArrayList<>();
		NewResourceDto createdResource = new NewResourceDto();
		createdResource.setName(validateName(resource.getName()));
		createdResource.setUrl(validateUrl(resource.getUrl()));
		createdResource.setActive(validateActive(resource.getActive()));
		createdResource.setCheckInterval(validateCheckInterval(resource.getCheckInterval()));
		createdResource.setObservers(validateObservers(resource.getObservers()));
		if (errors.isEmpty()) {
			return new ValidationResult(errors, createdResource);
		} else {
			return new ValidationResult(errors, null);
		}
	}

	private String validateName(String name) {
		if (name == null || name.trim().isEmpty()) {
			errors.add(Errors.NAME_REQUIRED);
			return null;
		}
		if (name.length() > NAME_MAX_LENGTH) {
			errors.add(Errors.NAME_TOO_LONG);
			return null;
		}
		//TODO Unique check
		return name;
	}

	private String validateUrl(String url) {
		if (url == null || url.trim().isEmpty()) {
			errors.add(Errors.URL_REQUIRED);
			return null;
		}
		if (url.length() > URL_MAX_LENGTH) {
			errors.add(Errors.URL_TOO_LONG);
			return null;
		}
		//TODO Unique check
		return url;
	}

	private boolean validateActive(String active) {
		return !(active == null || active.isEmpty());
	}

	private Integer validateCheckInterval(String checkInterval) {
		try {
			if (checkInterval == null || checkInterval.trim().isEmpty()) {
				errors.add(Errors.CHECK_INTERVAL_REQUIRED);
				return null;
			}
			int parsedCheckInterval = Integer.parseInt(checkInterval);
			if (parsedCheckInterval <= 0) {
				errors.add(Errors.CHECK_INTERVAL_GT_ZERO);
				return null;
			}
			return parsedCheckInterval;
		} catch (NumberFormatException e) {
			errors.add(Errors.CHECK_INTERVAL_NAN);
			return null;
		}
	}

	private List<String> validateObservers(String observers) {
		List<String> result = new ArrayList<>();
		if (observers == null) {
			return result;
		}
		String[] observerList = observers.replace("\r\n", "\n").replace("\r", "\n").split("\\n");
		for (String observer : observerList) {
			if (observer.trim().isEmpty()) {
				continue;
			}
			result.add(observer);
		}
		return result;
	}

}
