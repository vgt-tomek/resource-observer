package pl.vgtworld.resourceobserver.app.diff.dto;

import javax.ws.rs.FormParam;

public class DiffFormDto {

	@FormParam("first")
	private String first;

	@FormParam("second")
	private String second;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "DiffFormDto{" +
			  "first='" + first + '\'' +
			  ", second='" + second + '\'' +
			  '}';
	}

}
