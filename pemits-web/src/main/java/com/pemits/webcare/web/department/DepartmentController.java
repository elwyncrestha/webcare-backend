package com.pemits.webcare.web.department;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.department.service.DepartmentService;
import com.pemits.webcare.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.pemits.webcare.web.department.DepartmentController.URL;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
@RestController
@RequestMapping(URL)
@Slf4j
public class DepartmentController extends BaseController<Department, Long> {

    static final String URL = "/v1/departments";

    public DepartmentController(DepartmentService departmentService) {
        super(departmentService, log.getClass());
    }
}
