package com.pemits.webcare.api.helpdesk.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
public class HelpDeskRepositoryTest extends BaseJpaTest {

    private static final long MOCK_HELP_DESK_ID_1 = 1;
    private static final long MOCK_HELP_DESK_ID_2 = 2;
    private static final long MOCK_HELP_DESK_ID_3 = 3;

    @Autowired
    private HelpDeskRepository helpDeskRepository;

    @Test
    public void testSaveShouldReturnSavedHelpDesk() {
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.setName("Smith");
        helpDesk.setEmail("smith@gmail.com");
        helpDesk.setContactNumber("1234567890");
        helpDesk.setQuery("My first query");

        HelpDesk saved = helpDeskRepository.save(helpDesk);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    public void testSaveAllShouldReturnAllSavedHelpDesk() {
        HelpDesk helpDesk1 = new HelpDesk();
        helpDesk1.setName("Smith");
        helpDesk1.setEmail("smith@gmail.com");
        helpDesk1.setContactNumber("1234567890");
        helpDesk1.setQuery("My first query");

        HelpDesk helpDesk2 = new HelpDesk();
        helpDesk2.setName("John");
        helpDesk2.setEmail("john@gmail.com");
        helpDesk2.setContactNumber("1234567890");
        helpDesk2.setQuery("My second query");

        List<HelpDesk> saved = helpDeskRepository.saveAll(Arrays.asList(helpDesk1, helpDesk2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }


    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testFindHelpDeskByIdShouldReturnHelpDesk() {
        final Optional<HelpDesk> helpDesk = helpDeskRepository.findById(MOCK_HELP_DESK_ID_1);

        assertThat(helpDesk.isPresent(), equalTo(true));
        assertThat(helpDesk.get().getName(), is("Smith"));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<HelpDesk> helpDeskList = helpDeskRepository.findAll();

        assertThat(helpDeskList.size(), greaterThan(0));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testGetOneShouldReturnHelpDesk() {
        final HelpDesk helpDesk = helpDeskRepository.getOne(MOCK_HELP_DESK_ID_2);

        assertThat(helpDesk.getName(), is("Jack"));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteByIdShouldDeleteHelpDesk() {
        long count = helpDeskRepository.count();

        helpDeskRepository.deleteById(MOCK_HELP_DESK_ID_3);

        assertThat(helpDeskRepository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteShouldDeleteHelpDesk() {
        long count = helpDeskRepository.count();

        final HelpDesk helpDesk = helpDeskRepository.getOne(MOCK_HELP_DESK_ID_2);
        helpDeskRepository.delete(helpDesk);

        assertThat(helpDeskRepository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        helpDeskRepository.deleteAll();

        assertThat(helpDeskRepository.findAll(), hasSize(0));
    }

}
