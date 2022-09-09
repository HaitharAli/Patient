package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsuranceInputDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2362599611959171313L;
	private String insuranceNo;
	private String insuranceAgent;
	private String claimNo;
	private String claimStatus;

}
