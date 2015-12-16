package pl.vgtworld.resourceobserver.core.diffutil;

import difflib.DiffUtils;
import difflib.Patch;
import pl.vgtworld.resourceobserver.core.diffutil.dto.DiffLine;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiffUtil {

	private static final int DEFAULT_CONTEXT_SIZE = 3;

	private DiffUtil() {
	}

	public static List<String> createUnifiedDiff(byte[] leftFile, byte[] rightFile, String leftName, String rightName) {
		return createUnifiedDiff(convertToLinesCollection(leftFile), convertToLinesCollection(rightFile), leftName, rightName);
	}

	public static List<String> createUnifiedDiff(byte[] leftFile, byte[] rightFile, String leftName, String rightName, int contextSize) {
		return createUnifiedDiff(convertToLinesCollection(leftFile), convertToLinesCollection(rightFile), leftName, rightName, contextSize);
	}

	public static List<String> createUnifiedDiff(List<String> leftFile, List<String> rightFile, String leftName, String rightName) {
		return createUnifiedDiff(leftFile, rightFile, leftName, rightName, DEFAULT_CONTEXT_SIZE);
	}

	public static List<String> createUnifiedDiff(List<String> leftFile, List<String> rightFile, String leftName, String rightName, int contextSize) {
		Patch diff = DiffUtils.diff(leftFile, rightFile);
		return DiffUtils.generateUnifiedDiff(leftName, rightName, leftFile, diff, contextSize);
	}

	public static List<DiffLine> createColorCodedUnifiedDiff(byte[] leftFile, byte[] rightFile, String leftName, String rightName) {
		return createColorCodedUnifiedDiff(convertToLinesCollection(leftFile), convertToLinesCollection(rightFile), leftName, rightName);
	}

	public static List<DiffLine> createColorCodedUnifiedDiff(byte[] leftFile, byte[] rightFile, String leftName, String rightName, int contextSize) {
		return createColorCodedUnifiedDiff(convertToLinesCollection(leftFile), convertToLinesCollection(rightFile), leftName, rightName, contextSize);
	}

	public static List<DiffLine> createColorCodedUnifiedDiff(List<String> leftFile, List<String> rightFile, String leftName, String rightName) {
		return createColorCodedUnifiedDiff(leftFile, rightFile, leftName, rightName, DEFAULT_CONTEXT_SIZE);
	}

	public static List<DiffLine> createColorCodedUnifiedDiff(List<String> leftFile, List<String> rightFile, String leftName, String rightName, int contextSize) {
		return ColorDiffConverter.colorizeUnifiedDiff(createUnifiedDiff(leftFile, rightFile, leftName, rightName, contextSize));
	}

	private static List<String> convertToLinesCollection(byte[] input) {
		Scanner scanner = new Scanner(new ByteArrayInputStream(input));
		List<String> lines = new ArrayList<>();
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		return lines;
	}
}
