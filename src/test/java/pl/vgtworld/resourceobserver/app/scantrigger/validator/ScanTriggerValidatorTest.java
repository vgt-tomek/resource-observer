package pl.vgtworld.resourceobserver.app.scantrigger.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.vgtworld.resourceobserver.app.scantrigger.dto.ScanTriggerFormDto;
import pl.vgtworld.resourceobserver.app.scantrigger.dto.ScanTriggerFormDtoBuilder;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScanTriggerValidatorTest {

	private static final int EXAMPLE_RESOURCE_ID = 8;

	@Mock
	private ResourceService resourceService;

	@Before
	public void init() {
		when(resourceService.findById(eq(EXAMPLE_RESOURCE_ID))).thenReturn(new Resource());
	}

	@Test
	public void shouldAcceptValidForm() {
		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);
		ScanTriggerFormDto formDto = new ScanTriggerFormDtoBuilder()
			  .createValidFormDto()
			  .withResourceId("" + EXAMPLE_RESOURCE_ID)
			  .build();

		ScanTriggerValidator.Result result = validator.validate(formDto);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isTrue();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getResourceId()).isEqualTo(EXAMPLE_RESOURCE_ID);
	}

	@Test
	public void shouldNotAcceptMissingForm() {
		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);

		ScanTriggerValidator.Result result = validator.validate(null);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ScanTriggerValidator.Errors.FORM_MISSING);
		assertThat(result.getResourceId()).isNull();
	}

	@Test
	public void shouldNotAcceptMissingResourceId() {
		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);
		ScanTriggerFormDto formDto = new ScanTriggerFormDtoBuilder()
			  .createValidFormDto()
			  .withResourceId(null)
			  .build();

		ScanTriggerValidator.Result result = validator.validate(formDto);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ScanTriggerValidator.Errors.RESOURCE_ID_REQUIRED);
		assertThat(result.getResourceId()).isNull();
	}

	@Test
	public void shouldNotAcceptNANAsResourceId() {
		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);
		ScanTriggerFormDto formDto = new ScanTriggerFormDtoBuilder()
			  .createValidFormDto()
			  .withResourceId("notanumber")
			  .build();

		ScanTriggerValidator.Result result = validator.validate(formDto);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ScanTriggerValidator.Errors.RESOURCE_ID_NAN);
		assertThat(result.getResourceId()).isNull();
	}

	@Test
	public void shouldNotAcceptNotExistingResource() {
		ScanTriggerValidator validator = new ScanTriggerValidator(resourceService);
		ScanTriggerFormDto formDto = new ScanTriggerFormDtoBuilder()
			  .createValidFormDto()
			  .withResourceId("" + EXAMPLE_RESOURCE_ID)
			  .build();
		when(resourceService.findById(eq(EXAMPLE_RESOURCE_ID))).thenReturn(null);

		ScanTriggerValidator.Result result = validator.validate(formDto);

		assertThat(result).isNotNull();
		assertThat(result.isValid()).isFalse();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ScanTriggerValidator.Errors.RESOURCE_NOT_EXIST);
		assertThat(result.getResourceId()).isNull();
	}

}