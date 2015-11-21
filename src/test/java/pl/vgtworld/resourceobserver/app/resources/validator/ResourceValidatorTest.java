package pl.vgtworld.resourceobserver.app.resources.validator;

import org.junit.Test;
import pl.vgtworld.resourceobserver.app.resources.ResourceBuilder;
import pl.vgtworld.resourceobserver.app.resources.ResourceFormDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceValidatorTest {

	private static final String EXAMPLE_STRING_25_CHARACTERS = "abcdefghijklmnopqrstuvwxy";

	private ResourceValidator validator = new ResourceValidator();


	@Test
	public void shouldAcceptValidResourceFormDto() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
	}

	@Test
	public void shouldNotAcceptMissingName() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName(null)
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyName() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName("")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyAsName() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName("	 	")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptTooLongName() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName(EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  "a"
			  )
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_TOO_LONG);
	}

	@Test
	public void shouldNotAcceptMissingUrl() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl(null)
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyUrl() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl("")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyUrl() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl("	 	")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptTooLongUrl() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl(EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  "a"
			  )
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_TOO_LONG);
	}

	@Test
	public void shouldAcceptMissingActiveAsFalse() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive(null)
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isFalse();
	}

	@Test
	public void shouldAcceptEmptyActiveAsFalse() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive("")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isFalse();
	}

	@Test
	public void shouldAcceptOnActiveAsTrue() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive("on")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isTrue();
	}

	@Test
	public void shouldNotAcceptMissingCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval(null)
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("	 	")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNanCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("abc")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_NAN);
	}

	@Test
	public void shouldNotAcceptZeroCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("0")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_GT_ZERO);
	}

	@Test
	public void shouldNotAcceptNegativeCheckInterval() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("-1")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_GT_ZERO);
	}

	@Test
	public void shoudAcceptMissingObserversAsEmptyList() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers(null)
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).isEmpty();
	}

	@Test
	public void shoudAcceptEmptyObserversAsEmptyList() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).isEmpty();
	}

	@Test
	public void shouldIgnoreEmptyLinesInObservers() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers("first", "", "second")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldIgnoreWhiteCharactersOnlyLinesInObservers() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers("first", "	 	", "second")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleMacNewLineCharacters() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\rsecond")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleUnixNewLineCharacters() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\nsecond")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleWindowsNewLineCharacters() {
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\r\nsecond")
			  .build();

		ValidationResult result = validator.validate(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

}