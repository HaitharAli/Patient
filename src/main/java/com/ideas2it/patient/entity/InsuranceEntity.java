package com.ideas2it.patient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "insurance")
@EqualsAndHashCode(callSuper = true)
public class InsuranceEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String insuranceNo;
	private String insuranceAgent;
	private String claimNo;
	private String claimStatus;

}
