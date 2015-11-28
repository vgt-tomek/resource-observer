package pl.vgtworld.resourceobserver.storage.notification.resourcechange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "notifications_resource_change")
public class NotificationResourceChange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resource_id", nullable = false)
	private Integer resourceId;

	@Column(name = "snapshot_old_id", nullable = false)
	private Integer snapshotOldId;

	@Column(name = "snapshot_new_id", nullable = false)
	private Integer snapshotNewId;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "sent_at", nullable = true)
	private Date sentAt;

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

}
