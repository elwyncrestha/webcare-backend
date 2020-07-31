package com.pemits.webcare.api.feedback.repository;

import com.pemits.webcare.api.feedback.entity.Feedback;
import com.pemits.webcare.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends BaseRepository<Feedback, Long> {
}
