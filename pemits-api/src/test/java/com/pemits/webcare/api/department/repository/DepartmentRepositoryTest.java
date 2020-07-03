package com.pemits.webcare.api.department.repository;

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
import com.pemits.webcare.api.department.entity.Department;

public class DepartmentRepositoryTest extends BaseJpaTest {

    private static final long MOCK_OPD_DEPARTMENT_ID = 1L;
    private static final String MOCK_OPD_DEPARTMENT_NAME = "OPD";

    @Autowired
    private DepartmentRepository repository;

    @Test
    public void testSaveShouldReturnSavedDepartment() {
        Department department = new Department();
        department.setName(MOCK_OPD_DEPARTMENT_NAME);

        Department saved = repository.save(department);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    public void testSaveAllShouldReturnAllSavedDepartment() {
        Department department1 = new Department();
        department1.setName("OPD");

        Department department2 = new Department();
        department2.setName("Radiology");

        List<Department> saved = repository.saveAll(Arrays.asList(department1, department2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testSaveDepartmentShouldUpdateDepartment() {
        final Optional<Department> department = repository.findById(MOCK_OPD_DEPARTMENT_ID);

        assertThat(department.isPresent(), equalTo(true));

        String update = "OPD-2";
        department.get().setName(update);

        repository.save(department.get());

        assertThat(repository.getOne(MOCK_OPD_DEPARTMENT_ID).getName(), is(update));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testFindDepartmentByIdShouldReturnDepartment() {
        final Optional<Department> department = repository.findById(MOCK_OPD_DEPARTMENT_ID);

        assertThat(department.isPresent(), equalTo(true));
        assertThat(department.get().getName(), is(MOCK_OPD_DEPARTMENT_NAME));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Department> departments = repository.findAll();

        assertThat(departments.size(), equalTo(1));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testGetOneShouldReturnDepartment() {
        final Department department = repository.getOne(MOCK_OPD_DEPARTMENT_ID);

        assertThat(department.getName(), is(MOCK_OPD_DEPARTMENT_NAME));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testDeleteByIdShouldDeleteDepartment() {
        repository.deleteById(MOCK_OPD_DEPARTMENT_ID);

        assertThat(repository.findAll(), hasSize(0));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testDeleteShouldDeleteDepartment() {
        final Department department = repository.getOne(MOCK_OPD_DEPARTMENT_ID);

        repository.delete(department);

        assertThat(repository.findAll(), hasSize(0));
    }

    @Test
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        repository.deleteAll();

        assertThat(repository.findAll(), hasSize(0));
    }
}
