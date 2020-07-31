package com.pemits.webcare.api.feedback.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.feedback.entity.Feedback;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @Author Mohammad Hussain
 * created on 7/31/2020
 */
public class FeedbackRepositoryTest extends BaseJpaTest {

    private static final long MOCK_FEEDBACK_ID_1 = 1L;
    private static final long MOCK_FEEDBACK_ID_2 = 2L;

    @Autowired
    private FeedbackRepository repository;

    @Test
    public void testSaveShouldReturnSavedFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFullName("John Doe");
        feedback.setPhoneNo("1234567890");
        feedback.setEmail("john_doe@gmail.com");
        feedback.setMessage("This is my message");
        Feedback saved = repository.save(feedback);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    public void testSaveAllShouldReturnAllSavedFeedback() {
        Feedback feedback1 = new Feedback();
        feedback1.setFullName("John Doe");
        feedback1.setPhoneNo("1234567890");
        feedback1.setEmail("john_doe@gmail.com");
        feedback1.setMessage("This is my message");

        Feedback feedback2 = new Feedback();
        feedback2.setFullName("Jack Smith");
        feedback2.setPhoneNo("1234567890");
        feedback2.setEmail("jack_smith@gmail.com");
        feedback2.setMessage("This is my feedback");

        List<Feedback> saved = repository.saveAll(Arrays.asList(feedback1, feedback2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testFindFeedbackByIdShouldReturnFeedback() {
        final Optional<Feedback> feedback = repository.findById(MOCK_FEEDBACK_ID_1);

        assertThat(feedback.isPresent(), equalTo(true));
        assertThat(feedback.get().getFullName(), is("John Doe"));
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Feedback> feedbackList = repository.findAll();

        assertThat(feedbackList.size(), greaterThan(0));
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testGetOneShouldReturnFeedback() {
        final Feedback feedback = repository.getOne(MOCK_FEEDBACK_ID_2);

        assertThat(feedback.getFullName(), is("Jack Smith"));
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testDeleteByIdShouldDeleteFeedback() {
        long count = repository.count();

        repository.deleteById(MOCK_FEEDBACK_ID_1);

        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testDeleteShouldDeleteFeedback() {
        long count = repository.count();

        final Feedback feedback = repository.getOne(MOCK_FEEDBACK_ID_2);
        repository.delete(feedback);

        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/feedback/feedback-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        repository.deleteAll();
        assertThat(repository.findAll(), hasSize(0));
    }
}
