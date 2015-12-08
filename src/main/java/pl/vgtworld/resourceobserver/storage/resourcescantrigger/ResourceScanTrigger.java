package pl.vgtworld.resourceobserver.storage.resourcescantrigger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "resource_scan_triggers")
@NamedQueries({
	  @NamedQuery(
			name = ResourceScanTrigger.QUERY_FIND_ACTIVE_FOR_RESOURCE,
			query = "SELECT rst FROM ResourceScanTrigger rst WHERE rst.resourceId = :RESOURCE_ID AND rst.status = 'NEW' ORDER BY rst.id ASC"
	  )
})
public class ResourceScanTrigger {

	static final String QUERY_FIND_ACTIVE_FOR_RESOURCE = "ResourceScanTrigger.findActiveForResource";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resource_id", nullable = false)
	private Integer resourceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "processed_at", nullable = true)
	private Date processedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(Date processedAt) {
		this.processedAt = processedAt;
	}

}
