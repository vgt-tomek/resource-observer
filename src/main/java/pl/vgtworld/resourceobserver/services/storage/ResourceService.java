package pl.vgtworld.resourceobserver.services.storage;

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
		saveObservers(resourceId, resource.getObservers());
	}

	public void updateExistingResource(int resourceId, NewResourceDto resource) {
		Resource entity = findById(resourceId);
		if (entity == null) {
			throw new IllegalStateException("Entity does not exist.");
		}
		entity.setName(resource.getName());
		entity.setUrl(resource.getUrl());
		entity.setActive(resource.getActive());
		entity.setCheckInterval(resource.getCheckInterval());
		resourceObserverDao.removeObserversForResource(resourceId);
		saveObservers(resourceId, resource.getObservers());
	}

	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	public Resource findById(int id) {
		return resourceDao.findById(id);
	}

	public boolean isNameAlreadyTaken(String name, Integer excludedId) {
		Resource entity = resourceDao.findByName(name);
		if (excludedId == null) {
			return entity != null;
		} else {
			return entity != null && !entity.getId().equals(excludedId);
		}
	}

	public boolean isUrlAlreadyTaken(String url, Integer excludedId) {
		Resource entity = resourceDao.findByUrl(url);
		if (excludedId == null) {
			return entity != null;
		} else {
			return entity != null && !entity.getId().equals(excludedId);
		}
	}

	public List<ResourceObserver> findAllObserversForResource(int resourceId) {
		return resourceObserverDao.findAllForResource(resourceId);
	}

	private void saveObservers(int resourceId, List<String> observers) {
		for (String observerEmail : observers) {
			ResourceObserver observerEntity = new ResourceObserver();
			observerEntity.setResourceId(resourceId);
			observerEntity.setEmail(observerEmail);
			resourceObserverDao.create(observerEntity);
		}
	}

}
