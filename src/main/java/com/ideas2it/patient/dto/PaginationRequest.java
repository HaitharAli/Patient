package com.ideas2it.patient.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaginationRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1873244791187857844L;
	private int pageNo;
	private int pageSize;

}
