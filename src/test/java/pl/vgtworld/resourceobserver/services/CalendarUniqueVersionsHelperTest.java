package pl.vgtworld.resourceobserver.services;

import org.junit.Test;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersionObjectBuilder;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.ScanObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarUniqueVersionsHelperTest {

	@Test
	public void shouldReturnEmptyCollectionWhenNullIsUsedAsParameter() {
		CalendarUniqueVersionsHelper helper = new CalendarUniqueVersionsHelper();

		List<ResourceVersion> result = helper.extractUniqueVersionListBySnapshotId(null);

		assertThat(result).isEmpty();
	}

	@Test
	public void shouldFindTwoSeparateVersionsInCollection() {
		List<Scan> collection = new ArrayList<>();
		collection.add(createScan(8));
		collection.add(createScan(21));

		CalendarUniqueVersionsHelper helper = new CalendarUniqueVersionsHelper();

		List<ResourceVersion> result = helper.extractUniqueVersionListBySnapshotId(collection);

		assertThat(result).hasSize(2);
		assertThat(result.get(0).getSnapshotId()).isEqualTo(8);
		assertThat(result.get(1).getSnapshotId()).isEqualTo(21);
	}

	@Test
	public void shouldIgnoreDuplicateSnapshotIdWhenExtractingVersions() {
		List<Scan> collection = new ArrayList<>();
		collection.add(createScan(8));
		collection.add(createScan(21));
		collection.add(createScan(8));

		CalendarUniqueVersionsHelper helper = new CalendarUniqueVersionsHelper();

		List<ResourceVersion> result = helper.extractUniqueVersionListBySnapshotId(collection);

		assertThat(result).hasSize(2);
		assertThat(result.get(0).getSnapshotId()).isEqualTo(8);
		assertThat(result.get(1).getSnapshotId()).isEqualTo(21);
	}

	@Test
	public void shouldIgnoreScanWithoutVersion() {
		List<Scan> collection = new ArrayList<>();
		collection.add(createScan(8));
		collection.add(new ScanObjectBuilder().createInstanceWithoutVersion().build());
		collection.add(createScan(21));

		CalendarUniqueVersionsHelper helper = new CalendarUniqueVersionsHelper();

		List<ResourceVersion> result = helper.extractUniqueVersionListBySnapshotId(collection);

		assertThat(result).hasSize(2);
		assertThat(result.get(0).getSnapshotId()).isEqualTo(8);
		assertThat(result.get(1).getSnapshotId()).isEqualTo(21);
	}

	private Scan createScan(int snapshotId) {
		return new ScanObjectBuilder()
			  .createValidInstance()
			  .withResourceVersion(new ResourceVersionObjectBuilder()
						  .createValidInstance()
						  .withSnapshotId(snapshotId)
						  .build()
			  )
			  .build();
	}
}