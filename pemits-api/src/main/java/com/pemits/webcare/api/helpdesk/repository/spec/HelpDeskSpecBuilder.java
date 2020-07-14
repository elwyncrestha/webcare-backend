package com.pemits.webcare.api.helpdesk.repository.spec;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
public class HelpDeskSpecBuilder extends BaseSpecBuilder<HelpDesk> {

    public HelpDeskSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<HelpDesk> getSpecification(String property, String filterValue) {
        return new HelpDeskSpec(property, filterValue);
    }
}
