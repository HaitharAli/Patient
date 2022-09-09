package com.ideas2it.patient.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "patient")
@Data
@EqualsAndHashCode(callSuper = true)
public class PatientEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long medicalRecordNumber;
	private Date startCareDate;
	private Date birthDate;
	private String status;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String sex;
	private String maritalStatus;
	private String address;
	private String state;
	private String country;
	private Long zipCode;
	private String email;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referrer_id", referencedColumnName = "id")
    private ReferrerEntity refEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private DiagnosisEntity diagnosisEntity;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", referencedColumnName = "id")
    private Set<InsuranceEntity> insuranceEntity;
}
