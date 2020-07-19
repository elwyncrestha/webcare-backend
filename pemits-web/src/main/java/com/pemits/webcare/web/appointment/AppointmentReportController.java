package com.pemits.webcare.web.appointment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.appointment.entity.AppointmentReport;
import com.pemits.webcare.api.appointment.service.AppointmentReportService;
import com.pemits.webcare.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
@RestController
@RequestMapping(AppointmentReportController.URL)
@Slf4j
public class AppointmentReportController extends BaseController<AppointmentReport, Long> {

    static final String URL = "/v1/appointment-report";

    protected AppointmentReportController(
        AppointmentReportService service) {
        super(service, log.getClass());
    }
}
