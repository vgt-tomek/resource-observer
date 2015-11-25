package pl.vgtworld.resourceobserver.core;

import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarUtilTest {

	@Test
	public void shouldCreateProperWeekDayListForPolishLocale() {
		Locale locale = new Locale("pl", "PL");

		List<String> result = CalendarUtil.getWeekDayNames(locale);

		assertThat(result).hasSize(7);
		assertThat(result.get(0)).isEqualTo("Pn");
		assertThat(result.get(6)).isEqualTo("N");
	}

	@Test
	public void shouldCreateProperWeekDayListForUsLocale() {
		Locale locale = new Locale("en", "US");

		List<String> result = CalendarUtil.getWeekDayNames(locale);

		assertThat(result).hasSize(7);
		assertThat(result.get(0)).isEqualTo("Sun");
		assertThat(result.get(6)).isEqualTo("Mon");
	}

}