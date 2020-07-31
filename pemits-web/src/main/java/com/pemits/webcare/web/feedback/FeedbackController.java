package com.pemits.webcare.web.feedback;

import com.pemits.webcare.api.feedback.entity.Feedback;
import com.pemits.webcare.api.feedback.service.FeedbackService;
import com.pemits.webcare.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pemits.webcare.web.feedback.FeedbackController.URL;

@RestController
@RequestMapping(URL)
@Slf4j
public class FeedbackController extends BaseController<Feedback, Long> {

    static final String URL = "/v1/feedback";

    public FeedbackController(FeedbackService feedbackService) {
        super(feedbackService, log.getClass());
    }
}
