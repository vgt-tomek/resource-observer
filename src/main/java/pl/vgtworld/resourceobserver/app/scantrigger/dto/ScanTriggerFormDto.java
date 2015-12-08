package pl.vgtworld.resourceobserver.app.scantrigger.dto;

import javax.ws.rs.FormParam;

public class ScanTriggerFormDto {

	@FormParam("resourceId")
	private String resourceId;

	@FormParam("manualScanTriggerSubmit")
	private String manualScanTriggerSubmit;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getManualScanTriggerSubmit() {
		return manualScanTriggerSubmit;
	}

	public void setManualScanTriggerSubmit(String manualScanTriggerSubmit) {
		this.manualScanTriggerSubmit = manualScanTriggerSubmit;
	}

	@Override
	public String toString() {
		return "ScanTriggerFormDto{" +
			  "resourceId='" + resourceId + '\'' +
			  ", manualScanTriggerSubmit='" + manualScanTriggerSubmit + '\'' +
			  '}';
	}

}
