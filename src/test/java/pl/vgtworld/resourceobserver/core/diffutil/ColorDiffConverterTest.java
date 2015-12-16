package pl.vgtworld.resourceobserver.core.diffutil;

import org.junit.Test;
import pl.vgtworld.resourceobserver.core.diffutil.dto.DiffLine;
import pl.vgtworld.resourceobserver.core.diffutil.dto.Type;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorDiffConverterTest {

	@Test
	public void shouldCreateColorCodedUnifiedDiff() {
		List<String> unifiedDiff = createUnifiedDiff();

		List<DiffLine> result = ColorDiffConverter.colorizeUnifiedDiff(unifiedDiff);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(6);
		assertThat(result.get(0).getType()).isEqualTo(Type.META);
		assertThat(result.get(1).getType()).isEqualTo(Type.META);
		assertThat(result.get(2).getType()).isEqualTo(Type.META);
		assertThat(result.get(3).getType()).isEqualTo(Type.DEFAULT);
		assertThat(result.get(4).getType()).isEqualTo(Type.ADDITION);
		assertThat(result.get(5).getType()).isEqualTo(Type.DEFAULT);
	}

	private List<String> createUnifiedDiff() {
		List<String> diff = new ArrayList<>();
		diff.add("--- old.txt");
		diff.add("+++ new.txt");
		diff.add("@@ -2,2 +2,3 @@");
		diff.add(" 2nd");
		diff.add("+new");
		diff.add(" 3rd");
		return diff;
	}
}