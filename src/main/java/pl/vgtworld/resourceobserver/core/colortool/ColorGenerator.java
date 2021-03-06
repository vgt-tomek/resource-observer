package pl.vgtworld.resourceobserver.core.colortool;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorGenerator {

	public List<Color> generateColors(int count) {
		List<Color> colors = new ArrayList<>();

		if (count <= 0) {
			return colors;
		}

		for (int i = 1; i <= count; ++i) {
			float hue = (float) i / count;
			colors.add(new Color(Color.HSBtoRGB(hue, 1, 1)));
		}

		return colors;
	}

}
