package com.pemits.webcare.api.doctor.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
@Repository
public interface DoctorRepository extends BaseRepository<Doctor, Long> {

}
