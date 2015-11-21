package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.services.dto.NewResourceDto;
import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.resource.ResourceDao;
import pl.vgtworld.resourceobserver.storage.resourceobserver.ResourceObserver;
import pl.vgtworld.resourceobserver.storage.resourceobserver.ResourceObserverDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class ResourceService {

	@EJB
	private ResourceDao resourceDao;

	@EJB
	private ResourceObserverDao resourceObserverDao;

	public void createNewResource(NewResourceDto resource) {
		Resource resourceEntity = new Resource();
		resourceEntity.setName(resource.getName());
		resourceEntity.setUrl(resource.getUrl());
		resourceEntity.setActive(resource.getActive());
		resourceEntity.setCheckInterval(resource.getCheckInterval());
		resourceEntity.setCreatedAt(new Date());
		Integer resourceId = resourceDao.create(resourceEntity);
		for (String observerEmail : resource.getObservers()) {
			ResourceObserver observerEntity = new ResourceObserver();
			observerEntity.setResourceId(resourceId);
			observerEntity.setEmail(observerEmail);
			resourceObserverDao.create(observerEntity);
		}
	}

	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	public boolean isNameAlreadyTaken(String name) {
		return resourceDao.findByName(name) != null;
	}

	public boolean isUrlAlreadyTaken(String url) {
		return resourceDao.findByUrl(url) != null;
	}

}
