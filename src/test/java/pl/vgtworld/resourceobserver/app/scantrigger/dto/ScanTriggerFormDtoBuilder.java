package pl.vgtworld.resourceobserver.app.scantrigger.dto;

public class ScanTriggerFormDtoBuilder {

	private String resourceId;

	private String manualScanTriggerSubmit;

	public ScanTriggerFormDtoBuilder createValidFormDto() {
		resourceId = "21";
		manualScanTriggerSubmit = "submit";
		return this;
	}

	public ScanTriggerFormDtoBuilder withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	public ScanTriggerFormDto build() {
		ScanTriggerFormDto formDto = new ScanTriggerFormDto();
		formDto.setResourceId(resourceId);
		formDto.setManualScanTriggerSubmit(manualScanTriggerSubmit);
		return formDto;
	}

}
