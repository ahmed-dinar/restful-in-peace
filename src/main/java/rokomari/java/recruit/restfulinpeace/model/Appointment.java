package rokomari.java.recruit.restfulinpeace.model;

import java.util.Date;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="appointment")
public class Appointment {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	@Column(name = "doctor_id", nullable=false)
	private String doctor_id;
	
	@Column(name = "patient_id", nullable=false)
	private String patient_id;
	
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_time;
	
	@Column
	private String prescription;
	

	@ManyToOne(optional=false)
	@JoinColumn(name = "doctor_id", referencedColumnName="id", nullable = false, updatable = false, insertable = false)
	private Doctor doctor;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "patient_id", referencedColumnName="id", nullable = false, updatable = false, insertable = false)
	private Patient patient;



	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctors() {
		return doctor;
	}

	public void setDoctors(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
}
