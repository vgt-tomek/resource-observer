package pl.vgtworld.resourceobserver.services;

import org.junit.Test;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.ScanObjectBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarGroupByDayHelperTest {

	@Test
	public void shouldCreateEmptyMapWhenNoScansIsProvided() {
		List<Scan> collection = new ArrayList<>();
		CalendarGroupByDayHelper helper = new CalendarGroupByDayHelper();

		Map<Integer, List<Scan>> result = helper.groupVersionsByDayOfMonth(collection);

		assertThat(result).isEmpty();
	}

	@Test
	public void shouldPutAllScansFromOneDayIntoSingleCollection() {
		final int dayOfMonth = 27;
		ScanObjectBuilder scanBuilder = new ScanObjectBuilder();
		List<Scan> collection = new ArrayList<>();
		collection.add(scanBuilder.createValidInstance(1, 2015, 11, dayOfMonth).build());
		collection.add(scanBuilder.createValidInstance(2, 2015, 11, dayOfMonth).build());
		CalendarGroupByDayHelper helper = new CalendarGroupByDayHelper();

		Map<Integer, List<Scan>> result = helper.groupVersionsByDayOfMonth(collection);

		assertThat(result).hasSize(1);
		assertThat(result).containsKey(dayOfMonth);
		assertThat(result.get(dayOfMonth)).hasSize(2);
	}

	@Test
	public void shouldCreateSeparateCollectionForEachDayWithScan() {
		ScanObjectBuilder scanBuilder = new ScanObjectBuilder();
		List<Scan> collection = new ArrayList<>();
		collection.add(scanBuilder.createValidInstance(1, 2015, 11, 8).build());
		collection.add(scanBuilder.createValidInstance(2, 2015, 11, 12).build());
		collection.add(scanBuilder.createValidInstance(2, 2015, 11, 21).build());
		collection.add(scanBuilder.createValidInstance(2, 2015, 11, 30).build());
		CalendarGroupByDayHelper helper = new CalendarGroupByDayHelper();

		Map<Integer, List<Scan>> result = helper.groupVersionsByDayOfMonth(collection);

		assertThat(result).hasSize(4);
		assertThat(result).containsKey(8);
		assertThat(result).containsKey(12);
		assertThat(result).containsKey(21);
		assertThat(result).containsKey(30);
	}

}