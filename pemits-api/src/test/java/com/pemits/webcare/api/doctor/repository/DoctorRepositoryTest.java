package com.pemits.webcare.api.doctor.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.department.repository.DepartmentRepository;
import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.user.repository.UserRepository;

public class DoctorRepositoryTest extends BaseJpaTest {

    private static final long MOCK_DOCTOR1_ID = 1L;
    private static final String MOCK_DOCTOR1_SPECIALIZATION_FIELD = "Dummy speciality 1";
    private static final long MOCK_DOCTOR2_ID = 2L;
    private static final String MOCK_DOCTOR2_SPECIALIZATION_FIELD = "Dummy speciality 2";

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testSaveShouldReturnSavedDoctor() {
        Doctor doctor = new Doctor();
        doctor.setUser(userRepository.getOne(1L));
        doctor.setDepartment(departmentRepository.getOne(1L));
        doctor.setSpecializationField(MOCK_DOCTOR1_SPECIALIZATION_FIELD);

        Doctor saved = repository.save(doctor);

        assertThat(saved.getId(), notNullValue());
        assertThat(repository.count(), equalTo(1L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testSaveAllShouldReturnAllSavedDoctors() {
        Doctor doctor1 = new Doctor();
        doctor1.setUser(userRepository.getOne(1L));
        doctor1.setDepartment(departmentRepository.getOne(1L));
        doctor1.setSpecializationField("Dummy speciality 1");

        Doctor doctor2 = new Doctor();
        doctor2.setUser(userRepository.getOne(2L));
        doctor2.setDepartment(departmentRepository.getOne(2L));
        doctor2.setSpecializationField("Dummy speciality 2");

        List<Doctor> saved = repository.saveAll(Arrays.asList(doctor1, doctor2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
        assertThat(repository.count(), equalTo(2L));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testSaveShouldUpdateDoctor() {
        Doctor doctor = repository.getOne(1L);

        assertThat(doctor.getSpecializationField(), is("Dummy speciality 1"));

        String update = "Dummy speciality 9";
        doctor.setSpecializationField(update);
        repository.save(doctor);

        assertThat(repository.getOne(1L).getSpecializationField(), is(update));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testFindDoctorByIdShouldReturnDoctor() {
        final Optional<Doctor> doctor = repository.findById(1L);

        assertThat(doctor.isPresent(), is(true));
        assertThat(doctor.get().getSpecializationField(), is("Dummy speciality 1"));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Doctor> doctors = repository.findAll();

        assertThat(doctors.size(), equalTo(4));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testGetOneShouldReturnDoctor() {
        final Doctor doctor = repository.getOne(MOCK_DOCTOR2_ID);

        assertThat(doctor.getSpecializationField(), is(MOCK_DOCTOR2_SPECIALIZATION_FIELD));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testDeleteByIdShouldDeleteDoctor() {
        long count = repository.count();

        repository.deleteById(MOCK_DOCTOR2_ID);

        assertThat(repository.findAll(), hasSize((int) (count - 1)));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testDeleteShouldDeleteDepartment() {
        long count = repository.count();

        final Doctor doctor = repository.getOne(MOCK_DOCTOR1_ID);
        repository.delete(doctor);

        assertThat(repository.findAll(), hasSize((int) (count - 1)));
    }

    @Test
    @DatabaseSetup("/dataset/user/users-of-type-doctor.xml")
    @DatabaseSetup("/dataset/department/department-config.xml")
    @DatabaseSetup("/dataset/doctor/doctor-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        repository.deleteAll();

        assertThat(repository.count(), is(0L));
    }
}
