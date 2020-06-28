package com.pemits.webcare.api.department.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
    @DatabaseSetup("/dataset/department/department-config.xml")
    public void testFindDepartmentByIdShouldReturnDepartment() {
        final Optional<Department> department = repository.findById(MOCK_OPD_DEPARTMENT_ID);

        assertThat(department.isPresent(), equalTo(true));
        assertThat(department.get().getName(), is(MOCK_OPD_DEPARTMENT_NAME));
    }
}
