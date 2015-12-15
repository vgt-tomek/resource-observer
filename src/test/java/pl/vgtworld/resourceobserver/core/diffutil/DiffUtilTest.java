package pl.vgtworld.resourceobserver.core.diffutil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiffUtilTest {

	@Test
	public void shouldCreateValidUnifiedDiff() {
		List<String> leftFile = createFile("1st", "2nd", "3rd", "4th");
		List<String> rightFile = createFile("1st", "2nd", "new", "3rd", "4th");

		List<String> unifiedDiff = DiffUtil.createUnifiedDiff(leftFile, rightFile, "old.txt", "new.txt");

		assertThat(unifiedDiff).isNotNull();
		assertThat(unifiedDiff).isNotEmpty();
		assertThat(unifiedDiff).contains("+new");
	}

	private List<String> createFile(String... lines) {
		List<String> file = new ArrayList<>();
		Collections.addAll(file, lines);
		return file;
	}
}