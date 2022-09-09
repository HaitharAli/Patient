package com.ideas2it.patient.util;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.ideas2it.patient.dto.DiagnosisInputDto;
import com.ideas2it.patient.dto.InsuranceInputDto;
import com.ideas2it.patient.dto.PatientInputDto;
import com.ideas2it.patient.dto.ReferrerInputDto;
import com.ideas2it.patient.entity.DiagnosisEntity;
import com.ideas2it.patient.entity.InsuranceEntity;
import com.ideas2it.patient.entity.PatientEntity;
import com.ideas2it.patient.entity.ReferrerEntity;

public class PatientEntityConverter implements Converter<PatientInputDto, PatientEntity> {

	@Override
	public PatientEntity convert(MappingContext<PatientInputDto, PatientEntity> context) {

		PatientInputDto patientInputDto = context.getSource();
		PatientEntity patientEntity = context.getDestination();

		if (patientEntity == null) {
			patientEntity = new PatientEntity();
		}
		patientEntity.setMedicalRecordNumber(patientInputDto.getMedicalRecordNumber());
		patientEntity.setAddress(patientInputDto.getAddress());
		patientEntity.setBirthDate(patientInputDto.getBirthDate());
		patientEntity.setCountry(patientInputDto.getCountry());
		patientEntity.setEmail(patientInputDto.getEmail());
		patientEntity.setFirstName(patientInputDto.getFirstName());
		patientEntity.setLastName(patientInputDto.getLastName());
		patientEntity.setMaritalStatus(patientInputDto.getMaritalStatus());
		patientEntity.setPhoneNumber(patientInputDto.getPhoneNumber());
		patientEntity.setSex(patientInputDto.getSex());
		patientEntity.setStartCareDate(patientInputDto.getStartCareDate());
		patientEntity.setState(patientInputDto.getState());
		patientEntity.setStatus(patientInputDto.getStatus());
		patientEntity.setZipCode(patientInputDto.getZipCode());

		ReferrerInputDto referrerInputDto = patientInputDto.getReferrerInputDto();
		if (referrerInputDto != null) {

			ReferrerEntity referrerEntity = new ReferrerEntity();

			referrerEntity.setEmail(referrerInputDto.getEmail());
			referrerEntity.setMobileNo(referrerInputDto.getMobileNo());
			referrerEntity.setReferrerName(referrerInputDto.getReferrerName());

			patientEntity.setRefEntity(referrerEntity);
		}

		DiagnosisInputDto diagnosisInputDto = patientInputDto.getDiagnosisInputDto();
		if (diagnosisInputDto != null) {

			DiagnosisEntity diagnosisEntity = new DiagnosisEntity();

			diagnosisEntity.setPrimaryDiagnosis(diagnosisInputDto.getPrimaryDiagnosis());
			diagnosisEntity.setSecondaryDiagnosis(diagnosisInputDto.getSecondaryDiagnosis());
			diagnosisEntity.setThirdDiagnosis(diagnosisInputDto.getThirdDiagnosis());

			patientEntity.setDiagnosisEntity(diagnosisEntity);
		}

		Set<InsuranceInputDto> insuranceInputDtos = patientInputDto.getInsuranceInputDto();

		if (insuranceInputDtos != null) {
			Set<InsuranceEntity> insuranceEntities = new HashSet<>();

			insuranceInputDtos.stream().forEach(ins -> {
				InsuranceEntity entity = new InsuranceEntity();
				entity.setClaimNo(ins.getClaimNo());
				entity.setClaimStatus(ins.getClaimStatus());
				entity.setInsuranceAgent(ins.getInsuranceAgent());
				entity.setInsuranceNo(ins.getInsuranceNo());
				insuranceEntities.add(entity);
			});
			patientEntity.setInsuranceEntity(insuranceEntities);
		}

		return patientEntity;
	}

}
