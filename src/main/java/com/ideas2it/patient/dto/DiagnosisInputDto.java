package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DiagnosisInputDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -532816419342063879L;
	private String primaryDiagnosis;
	private String secondaryDiagnosis;
	private String thirdDiagnosis;

}
