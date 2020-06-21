package com.pemits.webcare.api.user.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public class UserSpecBuilder extends BaseSpecBuilder<User> {

    public UserSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<User> getSpecification(String property, String filterValue) {
        return new UserSpec(property, filterValue);
    }
}
