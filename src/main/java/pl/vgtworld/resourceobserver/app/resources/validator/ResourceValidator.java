package pl.vgtworld.resourceobserver.app.resources.validator;

import pl.vgtworld.resourceobserver.app.resources.ResourceFormDto;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.dto.NewResourceDto;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResourceValidator {

	static class Errors {
		static final String NAME_REQUIRED = "Name is required.";
		static final String NAME_TOO_LONG = "Name maximum length is " + NAME_MAX_LENGTH + " characters.";
		static final String NAME_NOT_AVAILABLE = "Name already exist.";
		static final String URL_REQUIRED = "Url is required.";
		static final String URL_TOO_LONG = "Url maximum length is " + URL_MAX_LENGTH + " characters.";
		static final String URL_NOT_AVAILABLE = "Url already exist.";
		static final String URL_MALFORMED = "Url invalid format used.";
		static final String CHECK_INTERVAL_REQUIRED = "Check interval is required.";
		static final String CHECK_INTERVAL_NAN = "Check interval must be an integer.";
		static final String CHECK_INTERVAL_GT_ZERO = "Check interval must be greater than zero.";
		static final String OBSERVER_DUPLICATE = "At least one e-mail address is duplicated.";
		static final String OBSERVER_INVALID_ADDRESS = "%s is not valid address.";
	}

	private static final int NAME_MAX_LENGTH = 100;
	private static final int URL_MAX_LENGTH = 250;

	private List<String> errors;

	private ResourceService resourceService;

	public ResourceValidator(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ValidationResult validateNew(ResourceFormDto resource) {
		return validate(resource, null);
	}

	public ValidationResult validateEdit(ResourceFormDto resource, int resourceId) {
		return validate(resource, resourceId);
	}

	private ValidationResult validate(ResourceFormDto resource, Integer resourceId) {
		errors = new ArrayList<>();
		NewResourceDto createdResource = new NewResourceDto();
		createdResource.setName(validateName(resource.getName(), resourceId));
		createdResource.setUrl(validateUrl(resource.getUrl(), resourceId));
		createdResource.setActive(validateActive(resource.getActive()));
		createdResource.setCheckInterval(validateCheckInterval(resource.getCheckInterval()));
		createdResource.setObservers(validateObservers(resource.getObservers()));
		if (errors.isEmpty()) {
			return new ValidationResult(errors, createdResource);
		} else {
			return new ValidationResult(errors, null);
		}
	}

	private String validateName(String name, Integer resourceId) {
		if (name == null || name.trim().isEmpty()) {
			errors.add(Errors.NAME_REQUIRED);
			return null;
		}
		if (name.length() > NAME_MAX_LENGTH) {
			errors.add(Errors.NAME_TOO_LONG);
			return null;
		}
		if (resourceService.isNameAlreadyTaken(name, resourceId)) {
			errors.add(Errors.NAME_NOT_AVAILABLE);
			return null;
		}
		return name;
	}

	private String validateUrl(String url, Integer resourceId) {
		if (url == null || url.trim().isEmpty()) {
			errors.add(Errors.URL_REQUIRED);
			return null;
		}
		if (url.length() > URL_MAX_LENGTH) {
			errors.add(Errors.URL_TOO_LONG);
			return null;
		}
		if (resourceService.isUrlAlreadyTaken(url, resourceId)) {
			errors.add(Errors.URL_NOT_AVAILABLE);
			return null;
		}
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			errors.add(Errors.URL_MALFORMED);
			return null;
		}
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
			if (result.contains(observer)) {
				errors.add(Errors.OBSERVER_DUPLICATE);
				return new ArrayList<>();
			}
			try {
				new InternetAddress(observer);
			} catch (AddressException e) {
				errors.add(String.format(Errors.OBSERVER_INVALID_ADDRESS, observer));
				return new ArrayList<>();
			}
			result.add(observer);
		}
		return result;
	}

}
