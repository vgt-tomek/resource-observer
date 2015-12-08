package pl.vgtworld.resourceobserver.services.storage;

import pl.vgtworld.resourceobserver.storage.resourcescantrigger.ResourceScanTrigger;
import pl.vgtworld.resourceobserver.storage.resourcescantrigger.ResourceScanTriggerDao;
import pl.vgtworld.resourceobserver.storage.resourcescantrigger.Status;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class ResourceScanTriggerService {

	@EJB
	private ResourceScanTriggerDao triggerDao;

	public void createNewScanTrigger(int resourceId) {
		ResourceScanTrigger entity = new ResourceScanTrigger();
		entity.setResourceId(resourceId);
		entity.setStatus(Status.NEW);
		entity.setCreatedAt(new Date());
		triggerDao.create(entity);
	}

}
