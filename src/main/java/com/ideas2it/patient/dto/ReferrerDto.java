package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReferrerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8736593655756292543L;
	private Long referrerid;
	private String referrerName;
	private String email;
	private Long mobileNo;

}
