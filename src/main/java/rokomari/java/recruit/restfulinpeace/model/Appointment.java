package rokomari.java.recruit.restfulinpeace.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="appointment")
public class Appointment {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "doctor_id", nullable=false)
	private Long doctor_id;
	
	@NotNull
	@Column(name = "patient_id", nullable=false)
	private Long patient_id;
	
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Future
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private Date date_time;
	
	@Column
	private String prescription;
	

	@ManyToOne(optional=false)
	@JoinColumn(name = "doctor_id", referencedColumnName="id", nullable = false, updatable = false, insertable = false)
	private Doctor doctor;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "patient_id", referencedColumnName="id", nullable = false, updatable = false, insertable = false)
	private Patient patient;

	public Long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
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
