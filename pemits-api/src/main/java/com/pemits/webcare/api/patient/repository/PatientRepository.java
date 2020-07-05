package com.pemits.webcare.api.patient.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
@Repository
public interface PatientRepository extends BaseRepository<Patient, Long> {

}
