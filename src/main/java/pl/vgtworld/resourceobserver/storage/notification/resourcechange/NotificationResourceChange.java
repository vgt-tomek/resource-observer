package pl.vgtworld.resourceobserver.storage.notification.resourcechange;

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
@Table(name = "notifications_resource_change")
@NamedQueries({
	  @NamedQuery(
			name = NotificationResourceChange.QUERY_FIND_NOT_SENT,
			query = "SELECT n FROM NotificationResourceChange n WHERE n.status = 'NEW' ORDER BY n.id ASC"
	  )
})
public class NotificationResourceChange {

	static final String QUERY_FIND_NOT_SENT = "NotificationResourceChange.findNotSent";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resource_id", nullable = false)
	private Integer resourceId;

	@Column(name = "snapshot_old_id", nullable = false)
	private Integer snapshotOldId;

	@Column(name = "snapshot_new_id", nullable = false)
	private Integer snapshotNewId;

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

	public Integer getSnapshotOldId() {
		return snapshotOldId;
	}

	public void setSnapshotOldId(Integer snapshotOldId) {
		this.snapshotOldId = snapshotOldId;
	}

	public Integer getSnapshotNewId() {
		return snapshotNewId;
	}

	public void setSnapshotNewId(Integer snapshotNewId) {
		this.snapshotNewId = snapshotNewId;
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
