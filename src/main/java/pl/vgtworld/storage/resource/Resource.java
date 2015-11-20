package pl.vgtworld.storage.resource;

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
@Table(name = "resources")
@NamedQueries({
	  @NamedQuery(
			name = Resource.QUERY_FIND_ALL,
			query = "SELECT r FROM Resource r ORDER BY r.id"
	  )
})
public class Resource {

	static final String QUERY_FIND_ALL = "Resource.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "check_interval")
	private Integer checkInterval;

	@Column(name = "created_at")
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(Integer checkInterval) {
		this.checkInterval = checkInterval;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
