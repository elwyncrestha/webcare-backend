package com.pemits.webcare.web.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.patient.service.PatientService;
import com.pemits.webcare.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 7/10/2020
 */
@RestController
@RequestMapping(PatientController.URL)
@Slf4j
public class PatientController extends BaseController<Patient, Long> {

    static final String URL = "/v1/patient";

    protected PatientController(
        PatientService service) {
        super(service, log.getClass());
    }
}
