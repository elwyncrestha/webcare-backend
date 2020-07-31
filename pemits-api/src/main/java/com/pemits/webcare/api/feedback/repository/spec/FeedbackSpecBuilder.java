package com.pemits.webcare.api.feedback.repository.spec;

import com.pemits.webcare.api.feedback.entity.Feedback;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 7/30/2020
 */
public class FeedbackSpecBuilder extends BaseSpecBuilder<Feedback> {

    public FeedbackSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Feedback> getSpecification(String property, String filterValue) {
        return new FeedbackSpec(property, filterValue);
    }
}
