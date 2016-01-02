package pl.vgtworld.resourceobserver.app.diff.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.vgtworld.resourceobserver.app.diff.dto.DiffFormDto;
import pl.vgtworld.resourceobserver.core.jsptags.versionlink.ResourceVersion;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiffValidatorTest {

	private static final int FIRST_VERSION_NUMBER = 1;

	private static final int SECOND_VERSION_NUMBER = 3;

	private static final int FIRST_VERSION_SNAPSHOT_ID = 4;

	private static final int SECOND_VERSION_SNAPSHOT_ID = 8;

	private static final int NOT_EXISTING_VERSION_NUMBER = 255;

	@Mock
	private SnapshotService snapshotService;

	private DiffValidator validator;

	@Before
	public void setUp() {
		validator = new DiffValidator(snapshotService, createResourceVersions());
		when(snapshotService.findById(eq(FIRST_VERSION_SNAPSHOT_ID))).thenReturn(createSnapshot(FIRST_VERSION_SNAPSHOT_ID));
		when(snapshotService.findById(eq(SECOND_VERSION_SNAPSHOT_ID))).thenReturn(createSnapshot(SECOND_VERSION_SNAPSHOT_ID));
	}

	@Test
	public void shouldAcceptValidForm() {
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), String.valueOf(SECOND_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isTrue();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getFirstSnapshot()).isNotNull();
		assertThat(result.getFirstSnapshot().getId()).isEqualTo(FIRST_VERSION_SNAPSHOT_ID);
		assertThat(result.getSecondSnapshot()).isNotNull();
		assertThat(result.getSecondSnapshot().getId()).isEqualTo(SECOND_VERSION_SNAPSHOT_ID);
	}

	@Test
	public void shouldNotAcceptMissingFormData() {
		DiffValidator.Result result = validator.validate(null);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FORM_DATA_MISSING);
	}

	@Test
	public void shouldNotAcceptMissingFirstVersionNumber() {
		DiffFormDto form = createForm(null, String.valueOf(SECOND_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FIRST_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNotExistingFirstVersionNumber() {
		DiffFormDto form = createForm(String.valueOf(NOT_EXISTING_VERSION_NUMBER), String.valueOf(SECOND_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FIRST_UNKNOWN);
	}

	@Test
	public void shouldNotAcceptMissingSecondVersionNumber() {
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), null);

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SECOND_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNotExistingSecondVersionNumber() {
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), String.valueOf(NOT_EXISTING_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SECOND_UNKNOWN);
	}

	@Test
	public void shouldNotAcceptSameIdForBothVersions() {
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), String.valueOf(FIRST_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SAME_SNAPSHOT);
	}

	@Test
	public void shouldNotAcceptBinaryFileAsFirstVersion() {
		when(snapshotService.findById(eq(FIRST_VERSION_SNAPSHOT_ID))).thenReturn(createSnapshot(
			  FIRST_VERSION_SNAPSHOT_ID, new byte[]{0, 0, 0}
		));
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), String.valueOf(SECOND_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FIRST_BINARY);
	}

	@Test
	public void shouldNotAcceptBinaryFileAsSecondVersion() {
		when(snapshotService.findById(eq(SECOND_VERSION_SNAPSHOT_ID))).thenReturn(createSnapshot(
			  FIRST_VERSION_SNAPSHOT_ID, new byte[]{0, 0, 0}
		));
		DiffFormDto form = createForm(String.valueOf(FIRST_VERSION_NUMBER), String.valueOf(SECOND_VERSION_NUMBER));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SECOND_BINARY);
	}

	private Snapshot createSnapshot(int id) {
		return createSnapshot(id, new byte[0]);
	}

	private Snapshot createSnapshot(int id, byte[] resource) {
		Snapshot snapshot = new Snapshot();
		snapshot.setId(id);
		snapshot.setResource(resource);
		return snapshot;
	}

	private DiffFormDto createForm(String firstId, String secondId) {
		DiffFormDto form = new DiffFormDto();
		form.setFirst(firstId);
		form.setSecond(secondId);
		return form;
	}

	private List<ResourceVersion> createResourceVersions() {
		List<ResourceVersion> versions = new ArrayList<>();
		versions.add(createResourceVersion(FIRST_VERSION_NUMBER, FIRST_VERSION_SNAPSHOT_ID));
		versions.add(createResourceVersion(2, 128));
		versions.add(createResourceVersion(SECOND_VERSION_NUMBER, SECOND_VERSION_SNAPSHOT_ID));
		return versions;
	}

	private ResourceVersion createResourceVersion(int versionId, int snapshotId) {
		ResourceVersion version = new ResourceVersion();
		version.setVersionId(versionId);
		version.setSnapshotId(snapshotId);
		return version;
	}

}