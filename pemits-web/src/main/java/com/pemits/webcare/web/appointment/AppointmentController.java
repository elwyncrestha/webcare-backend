package com.pemits.webcare.web.appointment;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.appointment.service.AppointmentService;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.service.BaseService;
import com.pemits.webcare.web.department.DepartmentController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pemits.webcare.web.appointment.AppointmentController.URL;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@RestController
@RequestMapping(URL)
@Slf4j
public class AppointmentController extends BaseController<Appointment, Long> {
    static final String URL = "/v1/appointment";

    protected AppointmentController(AppointmentService appointmentService) {
        super(appointmentService, log.getClass());
    }
}
