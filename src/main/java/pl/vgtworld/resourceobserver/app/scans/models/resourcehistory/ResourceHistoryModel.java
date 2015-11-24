package pl.vgtworld.resourceobserver.app.scans.models.resourcehistory;

import pl.vgtworld.resourceobserver.dto.scans.Scan;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import java.util.List;

public class ResourceHistoryModel {

	private Resource resource;

	private List<Scan> scans;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Scan> getScans() {
		return scans;
	}

	public void setScans(List<Scan> scans) {
		this.scans = scans;
	}

}
