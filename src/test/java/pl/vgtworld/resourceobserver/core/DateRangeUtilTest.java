package pl.vgtworld.resourceobserver.core;

import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class DateRangeUtilTest {

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Test
	public void shouldCreateMonthDateRange() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		DateRangeUtil.DateRange result = DateRangeUtil.createRangeForMonth(2015, 6);
		String startDate = sdf.format(result.getStartDate());
		String endDate = sdf.format(result.getEndDate());

		assertThat(startDate).isEqualTo("2015-06-01 00:00:00");
		assertThat(endDate).isEqualTo("2015-06-30 23:59:59");
	}

	@Test
	public void shouldCreateMonthDateRangeForJanuary() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		DateRangeUtil.DateRange result = DateRangeUtil.createRangeForMonth(2015, 1);
		String startDate = sdf.format(result.getStartDate());
		String endDate = sdf.format(result.getEndDate());

		assertThat(startDate).isEqualTo("2015-01-01 00:00:00");
		assertThat(endDate).isEqualTo("2015-01-31 23:59:59");
	}

	@Test
	public void shouldCreateMonthDateRangeForDecember() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		DateRangeUtil.DateRange result = DateRangeUtil.createRangeForMonth(2015, 12);
		String startDate = sdf.format(result.getStartDate());
		String endDate = sdf.format(result.getEndDate());

		assertThat(startDate).isEqualTo("2015-12-01 00:00:00");
		assertThat(endDate).isEqualTo("2015-12-31 23:59:59");
	}

}