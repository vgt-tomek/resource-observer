package pl.vgtworld.resourceobserver.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.ScanObjectBuilder;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.services.dto.calendars.WeekVersionsRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceTest {

	@Mock
	private StatsService statsService;

	@InjectMocks
	private CalendarService calendarService;

	@Test
	public void shouldCreateValidMonthCalendarDtoForListOfScans() {
		Locale.setDefault(new Locale("pl", "PL"));
		when(statsService.findResourceVersionsInMonth(1, 2015, 11)).thenReturn(createExampleScanList());

		MonthVersionsTable result = calendarService.findResourceVersionsInMonth(1, 2015, 11);

		assertThat(result).isNotNull();
		List<WeekVersionsRow> weeks = result.getWeeks();
		assertThat(weeks).isNotNull();
		assertThat(weeks).hasSize(6);
		//Validate empty days at first week (month starting on Sunday)
		WeekVersionsRow firstWeek = weeks.get(0);
		assertThat(firstWeek.getDays()).hasSize(7);
		for (int i = 0; i < 6; ++i) {
			assertThat(firstWeek.getDays().get(i).getDayOfMonth()).isNull();
		}
		assertThat(firstWeek.getDays().get(6).getDayOfMonth()).isEqualTo(1);
		//Validate empty days at last week (month ending on Monday)
		WeekVersionsRow lastWeek = weeks.get(5);
		assertThat(lastWeek.getDays().get(0).getDayOfMonth()).isEqualTo(30);
		for (int i = 1; i < 6; ++i) {
			assertThat(lastWeek.getDays().get(i).getDayOfMonth()).isNull();
		}
		//Validate scans put into proper days.
		assertThat(weeks.get(1).getDays().get(6).getVersions()).hasSize(1);
		assertThat(weeks.get(1).getDays().get(6).getVersions().get(0).getSnapshotId()).isEqualTo(1);
		assertThat(weeks.get(2).getDays().get(3).getVersions()).hasSize(2);
		assertThat(weeks.get(2).getDays().get(3).getVersions().get(0).getSnapshotId()).isEqualTo(2);
		assertThat(weeks.get(2).getDays().get(3).getVersions().get(1).getSnapshotId()).isEqualTo(3);
		assertThat(weeks.get(3).getDays().get(5).getVersions()).hasSize(1);
		assertThat(weeks.get(3).getDays().get(5).getVersions().get(0).getSnapshotId()).isEqualTo(4);
	}

	private List<Scan> createExampleScanList() {
		ScanObjectBuilder scanBuilder = new ScanObjectBuilder();
		List<Scan> collection = new ArrayList<>();
		collection.add(scanBuilder.createValidInstance(1, 2015, 11, 8).build());
		collection.add(scanBuilder.createValidInstance(2, 2015, 11, 12).build());
		collection.add(scanBuilder.createValidInstance(3, 2015, 11, 12).build());
		collection.add(scanBuilder.createValidInstance(4, 2015, 11, 21).build());
		return collection;
	}
}