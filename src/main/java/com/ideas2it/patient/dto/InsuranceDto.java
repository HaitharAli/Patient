package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsuranceDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8215694547348425885L;

	private Long insuranceId;
	private String insuranceNo;
	private String insuranceAgent;
	private String claimNo;
	private String claimStatus;
}
