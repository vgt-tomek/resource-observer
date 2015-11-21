package pl.vgtworld.resourceobserver.storage.resource;

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
