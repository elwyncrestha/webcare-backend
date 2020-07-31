package com.pemits.webcare.api.feedback.service;

import com.pemits.webcare.api.feedback.entity.Feedback;
import com.pemits.webcare.api.feedback.repository.FeedbackRepository;
import com.pemits.webcare.api.feedback.repository.spec.FeedbackSpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Long> implements FeedbackService {

    public FeedbackServiceImpl(FeedbackRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<Feedback> getSpec(Map<String, String> filterParams) {
        return new FeedbackSpecBuilder(filterParams);
    }
}
