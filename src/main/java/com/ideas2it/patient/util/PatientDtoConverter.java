package com.ideas2it.patient.util;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.ideas2it.patient.dto.DiagnosisDto;
import com.ideas2it.patient.dto.InsuranceDto;
import com.ideas2it.patient.dto.PatientDto;
import com.ideas2it.patient.dto.ReferrerDto;
import com.ideas2it.patient.entity.DiagnosisEntity;
import com.ideas2it.patient.entity.InsuranceEntity;
import com.ideas2it.patient.entity.PatientEntity;
import com.ideas2it.patient.entity.ReferrerEntity;

public class PatientDtoConverter implements Converter<PatientEntity, PatientDto> {

	@Override
	public PatientDto convert(MappingContext<PatientEntity, PatientDto> context) {

		PatientEntity patientEntity = context.getSource();
		PatientDto patientDto = context.getDestination();

		if (patientDto == null) {
			patientDto = new PatientDto();
		}

		patientDto.setMedicalRecordNumber(patientEntity.getMedicalRecordNumber());
		patientDto.setAddress(patientEntity.getAddress());
		patientDto.setBirthDate(patientEntity.getBirthDate());
		patientDto.setCountry(patientEntity.getCountry());
		patientDto.setEmail(patientEntity.getEmail());
		patientDto.setFirstName(patientEntity.getFirstName());
		patientDto.setLastName(patientEntity.getLastName());
		patientDto.setMaritalStatus(patientEntity.getMaritalStatus());
		patientDto.setPhoneNumber(patientEntity.getPhoneNumber());
		patientDto.setSex(patientEntity.getSex());
		patientDto.setStartCareDate(patientEntity.getStartCareDate());
		patientDto.setState(patientEntity.getState());
		patientDto.setStatus(patientEntity.getStatus());
		patientDto.setPatientId(patientEntity.getId());
		patientDto.setZipCode(patientEntity.getZipCode());

		ReferrerEntity referrerEntity = patientEntity.getRefEntity();
		if (referrerEntity != null) {

			ReferrerDto referrerDto = new ReferrerDto();

			referrerDto.setEmail(referrerEntity.getEmail());
			referrerDto.setMobileNo(referrerEntity.getMobileNo());
			referrerDto.setReferrerid(referrerEntity.getId());
			referrerDto.setReferrerName(referrerEntity.getReferrerName());
			
			patientDto.setReferrerDto(referrerDto);
		}

		DiagnosisEntity diagnosisEntity = patientEntity.getDiagnosisEntity();
		if (diagnosisEntity != null) {

			DiagnosisDto diagnosisDto = new DiagnosisDto();

			diagnosisDto.setDiagnosisId(diagnosisEntity.getId());
			diagnosisDto.setPrimaryDiagnosis(diagnosisEntity.getPrimaryDiagnosis());
			diagnosisDto.setSecondaryDiagnosis(diagnosisEntity.getSecondaryDiagnosis());
			diagnosisDto.setThirdDiagnosis(diagnosisEntity.getThirdDiagnosis());
			
			patientDto.setDiagnosisDto(diagnosisDto);
		}

		Set<InsuranceEntity> insuranceEntities = patientEntity.getInsuranceEntity();

		if (insuranceEntities != null) {
			Set<InsuranceDto> insuranceDtos = new HashSet<>();

			insuranceEntities.stream().forEach(ins -> {
				InsuranceDto dto = new InsuranceDto();
				dto.setClaimNo(ins.getClaimNo());
				dto.setClaimStatus(ins.getClaimStatus());
				dto.setInsuranceAgent(ins.getInsuranceAgent());
				dto.setInsuranceId(ins.getId());
				dto.setInsuranceNo(ins.getInsuranceNo());
				insuranceDtos.add(dto);
			});
			patientDto.setInsuranceDto(insuranceDtos);
		}
		

		return patientDto;
	}

}
