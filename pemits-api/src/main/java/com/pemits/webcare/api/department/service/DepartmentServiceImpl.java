package com.pemits.webcare.api.department.service;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.department.repository.DepartmentRepository;
import com.pemits.webcare.api.department.repository.spec.DepartmentSpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements DepartmentService {

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        super(departmentRepository);
    }

    @Override
    protected BaseSpecBuilder<Department> getSpec(Map<String, String> filterParams) {
        return new DepartmentSpecBuilder(filterParams);
    }
}
