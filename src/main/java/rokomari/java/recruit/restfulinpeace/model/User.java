package rokomari.java.recruit.restfulinpeace.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import javax.persistence.JoinColumn;
import org.springframework.data.annotation.LastModifiedDate;

import rokomari.java.recruit.restfulinpeace.lib.annotations.UniqueEntry;
import rokomari.java.recruit.restfulinpeace.service.UserService;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=3, max=255, message = "{user.first_name.overflow}")
	private String first_name;
	
	@NotNull
	@Size(min=3, max=255, message = "{user.last_name.overflow}")
	private String last_name;
	
	@Email(message = "{user.email.invalid}")
	@UniqueEntry(fieldName = "email", service = UserService.class)
	private String email;
	
	@NotNull
	@Size(min=2, max=30, message = "{user.mobile.overflow}")
	private String mobile;
	
	@NotNull
	@Size(min=5, message="{user.password.tooshort}")
	private String password;
	
	@Column(nullable = true)
	private String verify_url;
	
	@NotNull
	@Column(columnDefinition = "TINYINT(1) default 0")
	private boolean verified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date created;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerify_url() {
		return verify_url;
	}
	public void setVerify_url(String verify_url) {
		this.verify_url = verify_url;
	}
	public boolean getVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
