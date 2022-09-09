package com.ideas2it.patient.service;

import java.util.List;
import java.util.Optional;

import com.ideas2it.patient.dto.PatientDto;
import com.ideas2it.patient.dto.PatientInputDto;

public interface PatientService {

	Optional<PatientDto> getPatientById(Long id, String loginUserId);

	List<PatientDto> getAllPatients(String loginUserId);

	List<PatientDto> getAllPatientsPaginated(int pageNo, int pageSize, String loginUserId);

	PatientDto addPatient(PatientInputDto patientInputDto, String loginUserId);

	PatientDto updatePatient(Long id, PatientInputDto patientInputDto, String loginUserId);

	PatientDto patchPatient(Long id, PatientInputDto patientInputDto, String loginUserId);

	Boolean deletePatient(Long id, String loginUserId);
}
