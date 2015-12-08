package pl.vgtworld.resourceobserver.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.storage.resourcescantrigger.ResourceScanTrigger;
import pl.vgtworld.resourceobserver.storage.resourcescantrigger.ResourceScanTriggerDao;
import pl.vgtworld.resourceobserver.storage.resourcescantrigger.Status;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class ResourceScanTriggerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceScanTriggerService.class);

	@EJB
	private ResourceScanTriggerDao triggerDao;

	public void createNewScanTrigger(int resourceId) {
		ResourceScanTrigger entity = new ResourceScanTrigger();
		entity.setResourceId(resourceId);
		entity.setStatus(Status.NEW);
		entity.setCreatedAt(new Date());
		triggerDao.create(entity);
	}

	public ResourceScanTrigger findActiveScanTriggerForResource(int resourceId) {
		return triggerDao.findActiveScanTriggerForResource(resourceId);
	}

	public void markTriggerAsProcessed(int id) {
		ResourceScanTrigger trigger = triggerDao.findById(id);
		if (trigger == null) {
			LOGGER.error("Unable to update trigger status. Trigger #{} does not exist.", id);
			return;
		}
		trigger.setStatus(Status.PROCESSED);
		trigger.setProcessedAt(new Date());
	}

}
