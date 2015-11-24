package pl.vgtworld.resourceobserver.core.colortool;

import org.junit.Test;

import java.awt.Color;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorGeneratorTest {

	@Test
	public void shouldCreateOneColorCollection() {
		ColorGenerator generator = new ColorGenerator();

		List<Color> colors = generator.generateColors(1);

		assertThat(colors).hasSize(1);
		assertThat(colors).contains(Color.RED);
	}

	@Test
	public void shouldNotFailWhenZeroColorsShouldBeGenerated() {
		ColorGenerator generator = new ColorGenerator();

		List<Color> colors = generator.generateColors(0);

		assertThat(colors).isEmpty();
	}

	@Test
	public void shouldGenerateThreeMainColors() {
		ColorGenerator generator = new ColorGenerator();

		List<Color> colors = generator.generateColors(3);

		assertThat(colors).hasSize(3);
		assertThat(colors).contains(Color.RED);
		assertThat(colors).contains(Color.GREEN);
		assertThat(colors).contains(Color.BLUE);
	}

	@Test
	public void shouldGenerateOneHundredColors() {
		ColorGenerator generator = new ColorGenerator();

		List<Color> colors = generator.generateColors(100);

		assertThat(colors).hasSize(100);
	}

}