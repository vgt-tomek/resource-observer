package pl.vgtworld.resourceobserver.app.resources.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.vgtworld.resourceobserver.app.resources.ResourceBuilder;
import pl.vgtworld.resourceobserver.app.resources.dto.ResourceFormDto;
import pl.vgtworld.resourceobserver.services.storage.ResourceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResourceValidatorTest {

	private static final String EXAMPLE_STRING_25_CHARACTERS = "abcdefghijklmnopqrstuvwxy";

	@Mock
	private ResourceService resourceService;

	@Before
	public void prepareMocks() {
		when(resourceService.isNameAlreadyTaken(anyString(), anyInt())).thenReturn(false);
		when(resourceService.isUrlAlreadyTaken(anyString(), anyInt())).thenReturn(false);
	}

	@Test
	public void shouldAcceptValidResourceFormDto() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
	}

	@Test
	public void shouldNotAcceptMissingName() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName(null)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyName() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName("")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyAsName() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName("	 	")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_REQUIRED);
	}

	@Test
	public void shouldNotAcceptTooLongName() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withName(EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  EXAMPLE_STRING_25_CHARACTERS +
						  "a"
			  )
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_TOO_LONG);
	}

	@Test
	public void ShouldNotAcceptAlreadyExistingName() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .build();
		when(resourceService.isNameAlreadyTaken(resource.getName(), null)).thenReturn(true);

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.NAME_NOT_AVAILABLE);
	}

	@Test
	public void shouldNotAcceptMissingUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl(null)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl("")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl("	 	")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptTooLongUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
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

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_TOO_LONG);
	}

	@Test
	public void shouldNotAcceptAlreadyExistingUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .build();
		when(resourceService.isUrlAlreadyTaken(resource.getUrl(), null)).thenReturn(true);

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_NOT_AVAILABLE);
	}

	@Test
	public void shouldNotAcceptMalformedUrl() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withUrl("Lorem ipsum")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.URL_MALFORMED);
	}

	@Test
	public void shouldAcceptMissingActiveAsFalse() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive(null)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isFalse();
	}

	@Test
	public void shouldAcceptEmptyActiveAsFalse() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive("")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isFalse();
	}

	@Test
	public void shouldAcceptOnActiveAsTrue() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withActive("on")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getActive()).isTrue();
	}

	@Test
	public void shouldNotAcceptMissingCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval(null)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptEmptyCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptWhiteCharactersOnlyCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("	 	")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_REQUIRED);
	}

	@Test
	public void shouldNotAcceptNanCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("abc")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_NAN);
	}

	@Test
	public void shouldNotAcceptZeroCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("0")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_GT_ZERO);
	}

	@Test
	public void shouldNotAcceptNegativeCheckInterval() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withCheckInterval("-1")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.CHECK_INTERVAL_GT_ZERO);
	}

	@Test
	public void shoudAcceptMissingObserversAsEmptyList() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers(null)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).isEmpty();
	}

	@Test
	public void shoudAcceptEmptyObserversAsEmptyList() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).isEmpty();
	}

	@Test
	public void shouldIgnoreEmptyLinesInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers("first", "", "second")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldIgnoreWhiteCharactersOnlyLinesInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers("first", "	 	", "second")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleMacNewLineCharactersInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\rsecond")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleUnixNewLineCharactersInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\nsecond")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldHandleWindowsNewLineCharactersInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withRawObservers("first\r\nsecond")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).isEmpty();
		assertThat(result.getCreatedResource()).isNotNull();
		assertThat(result.getCreatedResource().getObservers()).hasSize(2);
	}

	@Test
	public void shouldNotAllowForDuplicatesInObservers() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers("first", "second", "first")
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(ResourceValidator.Errors.OBSERVER_DUPLICATE);
	}

	@Test
	public void shouldValidateFormatOfObserverAddress() {
		ResourceValidator validator = new ResourceValidator(resourceService);
		String invalidObserver = "Lorem ipsum";
		ResourceFormDto resource = new ResourceBuilder()
			  .createValidResource()
			  .withObservers(invalidObserver)
			  .build();

		ResourceValidator.Result result = validator.validateNew(resource);

		assertThat(result).isNotNull();
		assertThat(result.getErrors()).hasSize(1);
		assertThat(result.getErrors()).contains(String.format(ResourceValidator.Errors.OBSERVER_INVALID_ADDRESS, invalidObserver));
	}

}