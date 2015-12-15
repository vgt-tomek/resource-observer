package pl.vgtworld.resourceobserver.app.diff.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.diff.dto.DiffFormDto;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class DiffValidator {

	//TODO create abstract class for validation result common code.
	public static class Result {

		private List<String> errors;

		private Snapshot firstSnapshot;

		private Snapshot secondSnapshot;

		public Result(List<String> errors, Snapshot firstSnapshot, Snapshot secondSnapshot) {
			this.errors = errors;
			this.firstSnapshot = firstSnapshot;
			this.secondSnapshot = secondSnapshot;
		}

		public List<String> getErrors() {
			return errors;
		}

		public Snapshot getFirstSnapshot() {
			return firstSnapshot;
		}

		public Snapshot getSecondSnapshot() {
			return secondSnapshot;
		}

		public boolean isValid() {
			return errors.isEmpty() && firstSnapshot != null && secondSnapshot != null;
		}

	}

	static abstract class Errors {
		static final String FORM_DATA_MISSING = "Form data is required.";
		static final String SAME_SNAPSHOT = "Left and right side has the same snapshot selected.";
		static final String FIRST_REQUIRED = "Left side version is required.";
		static final String FIRST_UNKNOWN = "Left side version is unknown.";
		static final String SECOND_REQUIRED = "Right side version is required.";
		static final String SECOND_UNKNOWN = "Right side version is unknown.";
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(DiffValidator.class);

	private List<String> errors;

	private SnapshotService snapshotService;

	private List<ResourceVersion> resourceVersions;

	public DiffValidator(SnapshotService snapshotService, List<ResourceVersion> resourceVersions) {
		this.snapshotService = snapshotService;
		this.resourceVersions = resourceVersions;
	}

	public Result validate(DiffFormDto form) {
		errors = new ArrayList<>();
		if (form == null) {
			errors.add(Errors.FORM_DATA_MISSING);
			return new Result(errors, null, null);
		}
		Snapshot firstSnapshot = validateFirstSnapshot(form.getFirst());
		Snapshot secondSnapshot = validateSecondSnapshot(form.getSecond());
		if (form.getFirst() != null && form.getFirst().equals(form.getSecond())) {
			errors.add(Errors.SAME_SNAPSHOT);
		}
		return new Result(errors, firstSnapshot, secondSnapshot);
	}

	private Snapshot validateFirstSnapshot(String versionNumber) {
		if (versionNumber == null) {
			errors.add(Errors.FIRST_REQUIRED);
			return null;
		}
		Snapshot firstSnapshot = findSnapshot(versionNumber);
		if (firstSnapshot == null) {
			errors.add(Errors.FIRST_UNKNOWN);
		}
		return firstSnapshot;
	}

	private Snapshot validateSecondSnapshot(String versionNumber) {
		if (versionNumber == null) {
			errors.add(Errors.SECOND_REQUIRED);
			return null;
		}
		Snapshot firstSnapshot = findSnapshot(versionNumber);
		if (firstSnapshot == null) {
			errors.add(Errors.SECOND_UNKNOWN);
		}
		return firstSnapshot;
	}

	private Snapshot findSnapshot(String versionNumber) {
		if (versionNumber == null) {
			return null;
		}
		try {
			int convertedId = Integer.parseInt(versionNumber);
			if (convertedId < 0 || convertedId > resourceVersions.size()) {
				return null;
			}
			return snapshotService.findById(resourceVersions.get(convertedId - 1).getSnapshotId());
		} catch (NumberFormatException e) {
			LOGGER.trace("Snapshot id is not a number.", e);
			return null;
		}
	}
}
