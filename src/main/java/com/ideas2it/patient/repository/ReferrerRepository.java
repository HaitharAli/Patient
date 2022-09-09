package com.ideas2it.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.patient.entity.ReferrerEntity;

@Repository
public interface ReferrerRepository extends JpaRepository<ReferrerEntity, Long> {

}
