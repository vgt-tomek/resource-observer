package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.list.ResourceModel;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.services.ScanService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.scan.Scan;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceListService {

	@EJB
	private ResourceService resourceService;

	@EJB
	private ScanService scanService;

	public List<ResourceModel> getResourceList() {
		List<Resource> entities = resourceService.findAll();
		return entities.stream().map(this::asResourceModel).collect(Collectors.toList());
	}

	private ResourceModel asResourceModel(Resource entity) {
		ResourceModel dto = new ResourceModel();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setActive(entity.getActive());
		dto.setCheckInterval(entity.getCheckInterval());
		dto.setLastCheckAt(getLastCheckForResource(entity.getId()));
		return dto;
	}

	private Date getLastCheckForResource(int resourceId) {
		Scan lastScan = scanService.findLastScanForResource(resourceId);
		if (lastScan == null) {
			return null;
		}
		return lastScan.getCreatedAt();
	}

}
