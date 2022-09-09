package com.ideas2it.patient.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class PatientDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962408239836021577L;
	private Long patientId;
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

	private ReferrerDto referrerDto;

	private DiagnosisDto diagnosisDto;

	private Set<InsuranceDto> insuranceDto;

}
