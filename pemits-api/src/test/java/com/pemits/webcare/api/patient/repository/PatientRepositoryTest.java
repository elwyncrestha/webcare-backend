package com.pemits.webcare.api.patient.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.user.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @Author Mohammad Hussain
 * created on 7/19/2020
 */
public class PatientRepositoryTest extends BaseJpaTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    public void testSaveShouldReturnSavedPatient() {
        Patient patient = new Patient();
        patient.setUser(userRepository.getOne(5L));
        patient.setAge(19);

        Patient saved = patientRepository.save(patient);

        assertThat(saved.getId(), notNullValue());
        assertThat(patientRepository.count(), equalTo(1L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    public void testSaveAllShouldReturnAllSavedPatient() {
        Patient patient1 = new Patient();
        patient1.setUser(userRepository.getOne(6L));
        patient1.setAge(22);

        Patient patient2 = new Patient();
        patient2.setUser(userRepository.getOne(7L));
        patient2.setAge(25);

        List<Patient> saved = patientRepository.saveAll(Arrays.asList(patient1, patient2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
        assertThat(patientRepository.count(), equalTo(2L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testSaveShouldUpdatePatient() {
        Patient patient = patientRepository.getOne(1L);

        assertThat(patient.getAge(), is(19));

        int update = 22;
        patient.setAge(update);
        patientRepository.save(patient);

        assertThat(patientRepository.getOne(1L).getAge(), is(update));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testFindPatientByIdShouldReturnPatient() {
        final Optional<Patient> patient = patientRepository.findById(1L);

        assertThat(patient.isPresent(), is(true));
        assertThat(patient.get().getId(), is(1L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Patient> patients = patientRepository.findAll();

        assertThat(patients.size(), equalTo(4));
    }


    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testGetOneShouldReturnPatient() {
        final Patient patient = patientRepository.getOne(2L);

        assertThat(patient.getId(), is(2L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testDeleteByIdShouldDeletePatient() {
        long count = patientRepository.count();

        patientRepository.deleteById(3L);

        assertThat(patientRepository.findAll(), hasSize((int) (count - 1)));
    }


    @Test
    @DatabaseSetup("/dataset/user/users-of-type-patient.xml")
    @DatabaseSetup("/dataset/patient/patient-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        patientRepository.deleteAll();

        assertThat(patientRepository.count(), is(0L));
    }
}
