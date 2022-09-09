package com.ideas2it.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.patient.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>{
	
	PatientEntity findByMedicalRecordNumber(Long medicalRecordNumber);

}
