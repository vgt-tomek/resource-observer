package pl.vgtworld.resourceobserver.app.diff.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.diff.dto.DiffFormDto;
import pl.vgtworld.resourceobserver.core.ctm.ContentTypeMapper;
import pl.vgtworld.resourceobserver.core.ctm.ResourceContentType;
import pl.vgtworld.resourceobserver.core.validation.AbstractResult;
import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class DiffValidator {

	public static class Result extends AbstractResult {

		private Snapshot firstSnapshot;

		private Snapshot secondSnapshot;

		public Result(List<String> errors, Snapshot firstSnapshot, Snapshot secondSnapshot) {
			super(errors);
			this.firstSnapshot = firstSnapshot;
			this.secondSnapshot = secondSnapshot;
		}

		public Snapshot getFirstSnapshot() {
			return firstSnapshot;
		}

		public Snapshot getSecondSnapshot() {
			return secondSnapshot;
		}

		@Override
		public boolean isValid() {
			return super.isValid() && firstSnapshot != null && secondSnapshot != null;
		}

	}

	abstract static class Errors {
		static final String FORM_DATA_MISSING = "Form data is required.";
		static final String SAME_SNAPSHOT = "Left and right side has the same snapshot selected.";
		static final String FIRST_REQUIRED = "Left side version is required.";
		static final String FIRST_UNKNOWN = "Left side version is unknown.";
		static final String FIRST_BINARY = "Left side version is binary file.";
		static final String SECOND_REQUIRED = "Right side version is required.";
		static final String SECOND_UNKNOWN = "Right side version is unknown.";
		static final String SECOND_BINARY = "Right side version is binary file.";

		private Errors() {
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(DiffValidator.class);

	private List<String> errors;

	private SnapshotService snapshotService;

	private List<ResourceVersion> resourceVersions;

	private ContentTypeMapper contentTypeMapper = new ContentTypeMapper();

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
		Snapshot snapshot = findSnapshot(versionNumber);
		if (snapshot == null) {
			errors.add(Errors.FIRST_UNKNOWN);
			return null;
		}
		ResourceContentType contentType = contentTypeMapper.findContentTypeForResource(snapshot.getResource());
		if (contentType.isBinary()) {
			errors.add(Errors.FIRST_BINARY);
			return null;
		}
		return snapshot;
	}

	private Snapshot validateSecondSnapshot(String versionNumber) {
		if (versionNumber == null) {
			errors.add(Errors.SECOND_REQUIRED);
			return null;
		}
		Snapshot snapshot = findSnapshot(versionNumber);
		if (snapshot == null) {
			errors.add(Errors.SECOND_UNKNOWN);
			return null;
		}
		ResourceContentType contentType = contentTypeMapper.findContentTypeForResource(snapshot.getResource());
		if (contentType.isBinary()) {
			errors.add(Errors.SECOND_BINARY);
			return null;
		}
		return snapshot;
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
