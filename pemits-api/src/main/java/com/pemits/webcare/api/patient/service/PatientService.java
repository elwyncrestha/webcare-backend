package com.pemits.webcare.api.patient.service;

import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.core.service.BaseService;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
public interface PatientService extends BaseService<Patient, Long> {

    Patient findByUserId(long userId);
}
