package com.pemits.webcare.web.doctor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.doctor.service.DoctorService;
import com.pemits.webcare.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
@RestController
@RequestMapping(DoctorController.URL)
@Slf4j
public class DoctorController extends BaseController<Doctor, Long> {

    static final String URL = "/v1/doctors";
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        super(service, log.getClass());
        this.service = service;
    }
}
