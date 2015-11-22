package pl.vgtworld.resourceobserver.storage.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "scans")
@NamedQueries({
	  @NamedQuery(
			name = Scan.QUERY_FIND_NEWEST_FOR_RESOURCE,
			query = "SELECT s FROM Scan s WHERE s.resourceId = :RESOURCE_ID ORDER BY s.id DESC"
	  ),
	  @NamedQuery(
			name = Scan.QUERY_GET_COUNT_FOR_RESOURCE,
			query = "SELECT COUNT(s) FROM Scan s WHERE s.resourceId = :RESOURCE_ID"
	  ),
	  @NamedQuery(
			name = Scan.QUERY_GET_UNIQUE_SNAPSHOT_COUNT_FOR_RESOURCE,
			query = "SELECT COUNT(DISTINCT s.snapshotId) FROM Scan s WHERE s.resourceId = :RESOURCE_ID"
	  )
})
public class Scan {

	static final String QUERY_FIND_NEWEST_FOR_RESOURCE = "Scan.findNewestForResource";

	static final String QUERY_GET_COUNT_FOR_RESOURCE = "Scan.getCountForResource";

	static final String QUERY_GET_UNIQUE_SNAPSHOT_COUNT_FOR_RESOURCE = "Scan.getUniqueCountForResource";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resource_id")
	private Integer resourceId;

	@Column(name = "snapshot_id")
	private Integer snapshotId;

	@Column(name = "created_at")
	private Date createdAt;

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

	public Integer getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Scan{" +
			  "id=" + id +
			  ", resourceId=" + resourceId +
			  ", snapshotId=" + snapshotId +
			  ", createdAt=" + createdAt +
			  '}';
	}

}
