package rokomari.java.recruit.restfulinpeace.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="patient")
public class Patient {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String name;
	
	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String mobile;
	
	@Column
	@Type(type="org.hibernate.type.ShortType")
	private Short age;
	
	@Column(nullable = false, columnDefinition = "varchar(15)")
	private String gender;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String occupation;
	
	@Column
	@Type(type="text")
	private String symptom_summary;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date created;


	@OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getSymptom_summary() {
		return symptom_summary;
	}

	public void setSymptom_summary(String symptom_summary) {
		this.symptom_summary = symptom_summary;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	


}
