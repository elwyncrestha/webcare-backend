package com.pemits.webcare.api.notification.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.doctor.repository.DoctorRepository;
import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.api.patient.repository.PatientRepository;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.constant.AppConstant;
import com.pemits.webcare.core.enums.NotificationStatus;

public class NotificationRepositoryTest extends BaseJpaTest {

    private static final long MOCK_DOCTOR_ID = 1L;
    private static final long MOCK_PATIENT_ID = 1L;
    private static final long MOCK_PATIENT_ID_2 = 2L;
    private static final long MOCK_NOTIFICATION_ID = 1L;

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testSaveShouldReturnSavedNotification() {
        User doctor = doctorRepository.getOne(MOCK_DOCTOR_ID).getUser();
        User patient = patientRepository.getOne(MOCK_PATIENT_ID).getUser();

        Notification notification = Notification.builder()
            .status(NotificationStatus.UNSEEN)
            .from(patient)
            .to(doctor)
            .message(String.format(AppConstant.NOTIFY_NEW_APPOINTMENT, patient.getName()))
            .build();

        Notification saved = repository.save(notification);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testSaveAllShouldReturnSavedNotifications() {
        User doctor = doctorRepository.getOne(MOCK_DOCTOR_ID).getUser();
        User patient1 = patientRepository.getOne(MOCK_PATIENT_ID).getUser();
        User patient2 = patientRepository.getOne(MOCK_PATIENT_ID_2).getUser();

        Notification notification1 = Notification.builder()
            .status(NotificationStatus.UNSEEN)
            .from(patient1)
            .to(doctor)
            .message(String.format(AppConstant.NOTIFY_NEW_APPOINTMENT, patient1.getName()))
            .build();

        Notification notification2 = Notification.builder()
            .status(NotificationStatus.UNSEEN)
            .from(patient2)
            .to(doctor)
            .message(String.format(AppConstant.NOTIFY_NEW_APPOINTMENT, patient2.getName()))
            .build();

        List<Notification> saved = repository.saveAll(Arrays.asList(notification1, notification2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    @DatabaseSetup("/dataset/notification/notification-config.xml")
    public void testSaveShouldUpdateNotification() {
        Notification notification = repository.getOne(MOCK_NOTIFICATION_ID);

        NotificationStatus oldStatus = NotificationStatus.UNSEEN;
        NotificationStatus newStatus = NotificationStatus.SEEN;

        assertThat(notification.getStatus(), is(oldStatus));

        notification.setStatus(newStatus);
        repository.save(notification);

        Notification saved = repository.getOne(MOCK_NOTIFICATION_ID);

        assertThat(saved.getStatus(), not(oldStatus));
        assertThat(saved.getStatus(), is(newStatus));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup({
        "/dataset/user/users-of-type-doctor.xml",
        "/dataset/user/users-of-type-patient.xml"
    })
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    @DatabaseSetup("/dataset/notification/notification-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Notification> notifications = repository.findAll();

        assertThat(notifications.size(), greaterThan(0));
    }
}
