package com.pemits.webcare.api.department.repository.spec;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
public class DepartmentSpecBuilder extends BaseSpecBuilder<Department> {

    public DepartmentSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Department> getSpecification(String property, String filterValue) {
        return new DepartmentSpec(property, filterValue);
    }
}
