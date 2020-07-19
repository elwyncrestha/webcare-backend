package com.pemits.webcare.api.appointment.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.appointment.entity.AppointmentReport;

public class AppointmentReportRepositoryTest extends BaseJpaTest {


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    @DatabaseSetup("/dataset/appointment/appointment-config.xml")
    public void testSaveShouldReturnSavedAppointmentReport() {
        Appointment appointment = appointmentRepository.getOne(1L);
        AppointmentReport report = new AppointmentReport(appointment, "<h1>...</h1>");

        AppointmentReport saved = appointmentReportRepository.save(report);

        assertThat(saved.getId(), notNullValue());
        assertThat(appointmentReportRepository.count(), equalTo(1L));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    @DatabaseSetup("/dataset/appointment/appointment-config.xml")
    public void testSaveAllShouldReturnAllSavedAppointmentReport() {
        Appointment appointment1 = appointmentRepository.getOne(1L);
        AppointmentReport report1 = new AppointmentReport(appointment1, "<h1>...</h1>");

        Appointment appointment2 = appointmentRepository.getOne(2L);
        AppointmentReport report2 = new AppointmentReport(appointment2, "<h1>...</h1>");

        List<AppointmentReport> saved = appointmentReportRepository
            .saveAll(Arrays.asList(report1, report2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
        assertThat(appointmentReportRepository.count(), equalTo(2L));
    }
}
