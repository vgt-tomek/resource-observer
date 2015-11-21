package pl.vgtworld.resourceobserver.storage.snapshot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

	@Column(name = "binary_file")
	private Boolean binary;

	@Lob
	@Column(name = "resource")
	private byte[] resource;

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

	public Boolean getBinary() {
		return binary;
	}

	public void setBinary(Boolean binary) {
		this.binary = binary;
	}

	public byte[] getResource() {
		return resource;
	}

	public void setResource(byte[] resource) {
		this.resource = resource;
	}

}
