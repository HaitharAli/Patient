package com.ideas2it.patient.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ideas2it.patient.dto.PatientDto;
import com.ideas2it.patient.dto.PatientInputDto;
import com.ideas2it.patient.dto.UserDto;
import com.ideas2it.patient.entity.PatientEntity;
import com.ideas2it.patient.repository.PatientPaginationRepository;
import com.ideas2it.patient.repository.PatientRepository;
import com.ideas2it.patient.service.PatientService;
import com.ideas2it.patient.util.PatientDtoConverter;
import com.ideas2it.patient.util.PatientEntityConverter;

@Service
public class PatientServiceImpl implements PatientService {

	Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	PatientPaginationRepository paginationRepository;
	
	@Autowired
    private RestTemplate restTemplate;

    public UserDto getUserInfo(String userId) {
    	UserDto results = restTemplate.getForObject("http://user-service/getByUserId/"+userId, UserDto.class);
        return results;
    }

	@Override
	public Optional<PatientDto> getPatientById(Long id, String loginUserId) {
		Optional<PatientDto> patientDto = patientRepository.findById(id).map(this::convertPatientDto);
		return patientDto;
	}

	@Override
	public List<PatientDto> getAllPatients(String loginUserId) {
	//	UserDto results = getUserInfo(loginUserId);
	//	System.out.println("users information "+results);
		List<PatientDto> patientDtos = patientRepository.findAll().stream().map(this::convertPatientDto)
				.collect(Collectors.toList());
		
		System.out.println("paient dto "+patientDtos);
		return patientDtos;
	}

	@Override
	public List<PatientDto> getAllPatientsPaginated(int pageNo, int pageSize, String loginUserId) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		List<PatientDto> patientDtos = paginationRepository.findAll(paging).stream().map(this::convertPatientDto)
				.collect(Collectors.toList());
		return patientDtos;
	}

	@Override
	public PatientDto addPatient(PatientInputDto patientInputDto, String loginUserId) {
		// TODO Auto-generated method stub

		PatientEntity patientEntities = this.convertPatientEntity(patientInputDto);
		System.out.println("patient dto " + patientInputDto);
		System.out.println("patient tneity" + patientEntities);
		patientEntities.setCreatedBy(loginUserId);
		patientEntities.setModifiedBy(loginUserId);
		PatientEntity savedPatient = patientRepository.save(patientEntities);

		return convertPatientDto(savedPatient);
	}

	@Override
	public PatientDto updatePatient(Long id, PatientInputDto patientInputDto, String loginUserId) {
		// TODO Auto-generated method stub

		PatientEntity getPatient = patientRepository.findById(id).orElse(new PatientEntity());
		PatientEntity savedPatients = null;
		if (getPatient != null) {
			getPatient.setStatus(patientInputDto.getStatus());
			savedPatients = patientRepository.save(getPatient);
		}
		return convertPatientDto(savedPatients);
	}

	@Override
	public PatientDto patchPatient(Long id, PatientInputDto patientInputDto, String loginUserId) {
		PatientEntity patientEntities = convertPatientEntity(patientInputDto);

		Optional<PatientEntity> getPatient = patientRepository.findById(id);

		PatientEntity savedPatients = null;
		if (getPatient != null) {
			savedPatients = patientRepository.save(patientEntities);

		}
		return convertPatientDto(savedPatients);
	}

	@Override
	public Boolean deletePatient(Long id, String loginUserId) {
		Boolean b = false;
		logger.info("UserServiceImpl::deleteUser - Start");
		patientRepository.deleteById(id);
		b = true;
		logger.info("UserServiceImpl::deleteUser - End");
		return b;
	}

	public PatientDto convertPatientDto(PatientEntity patientEntity) {
		logger.info("PatientServiceImpl::convertPatientDto - Start");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addConverter(new PatientDtoConverter());
		logger.info("PatientServiceImpl::convertPatientDto - End");
		return modelMapper.map(patientEntity, PatientDto.class);
	}

	public PatientEntity convertPatientEntity(PatientInputDto patientInputDto) {
		logger.info("PatientServiceImpl::convertPatientDto - Start");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addConverter(new PatientEntityConverter());
		logger.info("PatientServiceImpl::convertPatientDto - End");
		return modelMapper.map(patientInputDto, PatientEntity.class);
	}

}
