package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.resource.ResourceDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ResourceService {

	@EJB
	private ResourceDao resourceDao;

	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

}
