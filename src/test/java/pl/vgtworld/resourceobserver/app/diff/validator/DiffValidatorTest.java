package pl.vgtworld.resourceobserver.app.diff.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.vgtworld.resourceobserver.app.diff.dto.DiffFormDto;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiffValidatorTest {

	private static final int FIRST_SNAPSHOT_ID = 8;

	private static final int SECOND_SNAPSHOT_ID = 21;

	@Mock
	private SnapshotService snapshotService;

	@Before
	public void setUp() {
		when(snapshotService.findById(eq(FIRST_SNAPSHOT_ID))).thenReturn(createSnapshot(FIRST_SNAPSHOT_ID));
		when(snapshotService.findById(eq(SECOND_SNAPSHOT_ID))).thenReturn(createSnapshot(SECOND_SNAPSHOT_ID));
	}

	@Test
	public void shouldAcceptValidForm() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm(String.valueOf(FIRST_SNAPSHOT_ID), String.valueOf(SECOND_SNAPSHOT_ID));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isTrue();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getFirstSnapshot()).isNotNull();
		assertThat(result.getFirstSnapshot().getId()).isEqualTo(FIRST_SNAPSHOT_ID);
		assertThat(result.getSecondSnapshot()).isNotNull();
		assertThat(result.getSecondSnapshot().getId()).isEqualTo(SECOND_SNAPSHOT_ID);
	}

	@Test
	public void shouldNotAcceptMissingFormData() {
		DiffValidator validator = new DiffValidator(snapshotService);

		DiffValidator.Result result = validator.validate(null);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FORM_DATA_MISSING);
	}

	@Test
	public void shouldNotAcceptMissingFirstSnapshotId() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm(null, String.valueOf(SECOND_SNAPSHOT_ID));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FIRST_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNotExistingFirstSnapshotId() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm("1", String.valueOf(SECOND_SNAPSHOT_ID));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.FIRST_UNKNOWN);
	}

	@Test
	public void shouldNotAcceptMissingSecondSnapshotId() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm(String.valueOf(FIRST_SNAPSHOT_ID), null);

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SECOND_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNotExistingSecondSnapshotId() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm(String.valueOf(FIRST_SNAPSHOT_ID), "1");

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SECOND_UNKNOWN);
	}

	@Test
	public void shouldNotAcceptSameIdForBothSnapshots() {
		DiffValidator validator = new DiffValidator(snapshotService);
		DiffFormDto form = createForm(String.valueOf(FIRST_SNAPSHOT_ID), String.valueOf(FIRST_SNAPSHOT_ID));

		DiffValidator.Result result = validator.validate(form);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(DiffValidator.Errors.SAME_SNAPSHOT);
	}

	private Snapshot createSnapshot(int id) {
		Snapshot snapshot = new Snapshot();
		snapshot.setId(id);
		return snapshot;
	}

	private DiffFormDto createForm(String firstId, String secondId) {
		DiffFormDto form = new DiffFormDto();
		form.setFirst(firstId);
		form.setSecond(secondId);
		return form;
	}

}