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
		assertThat(result.get(6)).isEqualTo("Sat");
	}

	@Test
	public void shouldCalculateEmptyDaysForMonthStartingOnSundayInPolishLocale() {
		Locale locale = new Locale("pl", "PL");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2015, 11, locale);

		assertThat(result).isEqualTo(6);
	}

	@Test
	public void shouldCalculateEmptyDaysMonthStartingOnSundayInUsLocale() {
		Locale locale = new Locale("en", "US");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2015, 11, locale);

		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldCalculateEmptyDaysForMonthStartingOnMondayInPolishLocale() {
		Locale locale = new Locale("pl", "PL");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2016, 2, locale);

		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldCalculateEmptyDaysMonthStartingOnMondayInUsLocale() {
		Locale locale = new Locale("en", "US");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2016, 2, locale);

		assertThat(result).isEqualTo(1);
	}

	@Test
	public void shouldCalculateEmptyDaysForMonthStartingOnWednesdayInPolishLocale() {
		Locale locale = new Locale("pl", "PL");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2016, 6, locale);

		assertThat(result).isEqualTo(2);
	}

	@Test
	public void shouldCalculateEmptyDaysMonthStartingOnWednesdayInUsLocale() {
		Locale locale = new Locale("en", "US");

		int result = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(2016, 6, locale);

		assertThat(result).isEqualTo(3);
	}

}