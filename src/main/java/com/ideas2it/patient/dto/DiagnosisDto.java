package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DiagnosisDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1260329948680145524L;
	private Long diagnosisId;
	private String primaryDiagnosis;
	private String secondaryDiagnosis;
	private String thirdDiagnosis;
}
