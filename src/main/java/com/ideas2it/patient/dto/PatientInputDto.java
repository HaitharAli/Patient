package com.ideas2it.patient.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class PatientInputDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2827361363460436703L;
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

	private ReferrerInputDto referrerInputDto;

	private DiagnosisInputDto diagnosisInputDto;

	private Set<InsuranceInputDto> insuranceInputDto;
}
