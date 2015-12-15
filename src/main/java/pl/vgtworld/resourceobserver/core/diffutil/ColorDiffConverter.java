package pl.vgtworld.resourceobserver.core.diffutil;

import pl.vgtworld.resourceobserver.core.diffutil.dto.DiffLine;
import pl.vgtworld.resourceobserver.core.diffutil.dto.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ColorDiffConverter {

	static List<DiffLine> coorizeUnifiedDiff(List<String> unifiedDiff) {
		List<DiffLine> result = new ArrayList<>(unifiedDiff.size());
		result.addAll(unifiedDiff.stream().map(ColorDiffConverter::colorizeDiffLine).collect(Collectors.toList()));
		return result;
	}

	private static DiffLine colorizeDiffLine(String line) {
		return new DiffLine(findTypeForLine(line), line);
	}

	private static Type findTypeForLine(String line) {
		if (line.startsWith("---") || line.startsWith("+++") || line.startsWith("@@")) {
			return Type.META;
		}
		if (line.startsWith("-")) {
			return Type.REMOVAL;
		}
		if (line.startsWith("+")) {
			return Type.ADDITION;
		}
		return Type.DEFAULT;
	}
}
