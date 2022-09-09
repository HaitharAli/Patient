package com.ideas2it.patient.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.patient.entity.PatientEntity;

@Repository
public interface PatientPaginationRepository extends PagingAndSortingRepository<PatientEntity, Long> {

}
