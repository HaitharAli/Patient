package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReferrerInputDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2210579989251172758L;
	private String referrerName;
	private String email;
	private Long mobileNo;

}
