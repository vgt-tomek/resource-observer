package pl.vgtworld.resourceobserver.storage.snapshot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "snapshots")
@NamedQueries({
	  @NamedQuery(
			name = Snapshot.QUERY_FIND_BY_HASH,
			query = "SELECT s FROM Snapshot s WHERE s.hash = :HASH"
	  )
})
public class Snapshot {

	static final String QUERY_FIND_BY_HASH = "Snapshot.findByHash";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "hash", length = 64)
	private String hash;

	@Column(name = "resource")
	private String resource;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
