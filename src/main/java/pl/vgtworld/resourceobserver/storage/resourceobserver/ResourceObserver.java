package pl.vgtworld.resourceobserver.storage.resourceobserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "resource_observers")
@NamedQueries({
	  @NamedQuery(
			name = ResourceObserver.QUERY_FIND_ALL_FOR_RESOURCE,
			query = "SELECT o FROM ResourceObserver o WHERE o.resourceId = :RESOURCE_ID ORDER BY o.id ASC"
	  )
})
public class ResourceObserver {

	static final String QUERY_FIND_ALL_FOR_RESOURCE = "ResourceObserver.findAllForResource";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resource_id")
	private Integer resourceId;

	@Column(name = "email")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
