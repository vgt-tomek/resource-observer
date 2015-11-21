package pl.vgtworld.resourceobserver.app.resources;

import pl.vgtworld.resourceobserver.app.resources.models.list.ResourceModel;
import pl.vgtworld.resourceobserver.services.ResourceService;
import pl.vgtworld.resourceobserver.storage.resource.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceListService {

	@EJB
	private ResourceService resourceService;

	public List<ResourceModel> getResourceList() {
		List<Resource> entities = resourceService.findAll();
		return entities.stream().map(ResourceListService::asResourceModel).collect(Collectors.toList());
	}

	private static ResourceModel asResourceModel(Resource entity) {
		ResourceModel dto = new ResourceModel();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setActive(entity.getActive());
		dto.setCheckInterval(entity.getCheckInterval());
		return dto;
	}
}
