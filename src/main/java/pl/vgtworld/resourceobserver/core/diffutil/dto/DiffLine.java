package pl.vgtworld.resourceobserver.core.diffutil.dto;

public class DiffLine {

	private Type type;

	private String line;

	public DiffLine(Type type, String line) {
		this.type = type;
		this.line = line;
	}

	public Type getType() {
		return type;
	}

	public String getLine() {
		return line;
	}

}
