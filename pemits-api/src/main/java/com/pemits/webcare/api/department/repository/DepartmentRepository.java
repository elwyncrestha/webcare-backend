package com.pemits.webcare.api.department.repository;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long> {
}
