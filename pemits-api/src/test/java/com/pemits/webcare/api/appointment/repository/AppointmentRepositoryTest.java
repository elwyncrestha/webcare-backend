package com.pemits.webcare.api.appointment.repository;

import static com.pemits.webcare.api.doctor.repository.spec.DoctorSpec.FILTER_BY_DEPARTMENT_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.department.repository.DepartmentRepository;
import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.doctor.repository.DoctorRepository;
import com.pemits.webcare.api.doctor.repository.spec.DoctorSpecBuilder;
import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.patient.repository.PatientRepository;
import com.pemits.webcare.core.enums.AppointmentStatus;

public class AppointmentRepositoryTest extends BaseJpaTest {

    private static final long MOCK_DEPARTMENT1_ID = 1L;
    private static final long MOCK_PATIENT1_ID = 1L;
    private static final long MOCK_PATIENT2_ID = 2L;
    private static final long MOCK_APPOINTMENT2_ID = 2L;
    private static final long MOCK_APPOINTMENT_ID = 1L;
    private static final LocalDate MOCK_APPOINTMENT_DATE = LocalDate.of(2020,07,12);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testSaveShouldReturnSavedAppointment() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.now().plusDays(5));
        appointment.setAppointmentTime(LocalTime.now().plusHours(3));

        // set the department
        Department department = departmentRepository.getOne(MOCK_DEPARTMENT1_ID);
        appointment.setDepartment(department);

        // set doctor based on the department
        Map<String, String> filter = new HashMap<>();
        filter.put(FILTER_BY_DEPARTMENT_ID, String.valueOf(MOCK_DEPARTMENT1_ID));
        Specification<Doctor> spec = new DoctorSpecBuilder(filter).build();
        List<Doctor> doctors = doctorRepository.findAll(spec);
        appointment.setDoctor(doctors.get(0));

        // set existing patient
        Patient patient = patientRepository.getOne(MOCK_PATIENT1_ID);
        appointment.setPatient(patient);

        Appointment saved = appointmentRepository.save(appointment);

        assertThat(saved.getId(), notNullValue());
        assertThat(appointmentRepository.count(), equalTo(1L));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testSaveAllShouldReturnAllSavedAppointments() {
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentDate(LocalDate.now().plusDays(5));
        appointment1.setAppointmentTime(LocalTime.now().plusHours(3));

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(LocalDate.now().plusDays(2));
        appointment2.setAppointmentTime(LocalTime.now().plusHours(1));

        // set the department
        Department department = departmentRepository.getOne(MOCK_DEPARTMENT1_ID);
        appointment1.setDepartment(department);
        appointment2.setDepartment(department);

        // set doctor based on the department
        Map<String, String> filter = new HashMap<>();
        filter.put(FILTER_BY_DEPARTMENT_ID, String.valueOf(MOCK_DEPARTMENT1_ID));
        Specification<Doctor> spec = new DoctorSpecBuilder(filter).build();

        List<Doctor> doctors = doctorRepository.findAll(spec);

        appointment1.setDoctor(doctors.get(0));
        appointment2.setDoctor(doctors.get(1));

        // set existing patient
        Patient patient1 = patientRepository.getOne(MOCK_PATIENT1_ID);
        appointment1.setPatient(patient1);

        Patient patient2 = patientRepository.getOne(MOCK_PATIENT2_ID);
        appointment2.setPatient(patient2);

        List<Appointment> saved = appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
        assertThat(appointmentRepository.count(), equalTo(2L));
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
    public void testFindAppointmentByIdShouldReturnAppointment() {
        final Optional<Appointment> appointment = appointmentRepository.findById(MOCK_APPOINTMENT_ID);

        assertThat(appointment.isPresent(), equalTo(true));
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
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Appointment> appointments = appointmentRepository.findAll();
        assertThat(appointments.size(), greaterThan(0));
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
    public void testGetOneShouldReturnAppointment() {
        final Appointment appointment = appointmentRepository.getOne(MOCK_APPOINTMENT_ID);
        assertThat(appointment.getAppointmentDate(), is(MOCK_APPOINTMENT_DATE));
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
    public void testDeleteByIdShouldDeleteAppointment() {
        long countAppointment = appointmentRepository.count();
        appointmentRepository.deleteById(MOCK_APPOINTMENT2_ID);
        assertThat(appointmentRepository.findAll(), hasSize((int) countAppointment -1));
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
    public void testDeleteShouldDeleteAppointment() {
        long countAppointment = appointmentRepository.count();
        final Appointment appointment = appointmentRepository.getOne(MOCK_APPOINTMENT_ID);
        appointmentRepository.delete(appointment);
        assertThat(appointmentRepository.findAll(), hasSize((int) countAppointment -1));
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
    public void testDeleteAllShouldReturnZeroAppointment() {
        appointmentRepository.deleteAll();
        assertThat(appointmentRepository.findAll(), hasSize(0));
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
    public void testSaveShouldUpdateAppointmentStatus() {
        Appointment appointment = appointmentRepository.getOne(MOCK_APPOINTMENT_ID);

        AppointmentStatus oldStatus = AppointmentStatus.PENDING;
        AppointmentStatus newStatus = AppointmentStatus.APPROVED;

        assertThat(appointment.getStatus(), is(oldStatus));

        appointment.setStatus(newStatus);
        appointmentRepository.save(appointment);

        Appointment saved = appointmentRepository.getOne(MOCK_APPOINTMENT_ID);

        assertThat(saved.getStatus(), not(oldStatus));
        assertThat(saved.getStatus(), is(newStatus));
    }
}
