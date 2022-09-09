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
@Table(name = "diagnosis")
@EqualsAndHashCode(callSuper = true)
public class DiagnosisEntity extends AuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String primaryDiagnosis;
	private String secondaryDiagnosis;
	private String thirdDiagnosis;

}
