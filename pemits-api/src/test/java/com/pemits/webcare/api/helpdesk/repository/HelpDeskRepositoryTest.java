package com.pemits.webcare.api.helpdesk.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.helpdesk.entity.HelpDesk;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
public class HelpDeskRepositoryTest extends BaseJpaTest {

    private static final long MOCK_HELP_DESK_ID_1 = 1;
    private static final long MOCK_HELP_DESK_ID_2 = 2;
    private static final long MOCK_HELP_DESK_ID_3 = 3;

    @Autowired
    private HelpDeskRepository repository;

    @Test
    public void testSaveShouldReturnSavedHelpDesk() {
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.setName("Smith");
        helpDesk.setEmail("smith@gmail.com");
        helpDesk.setContactNumber("1234567890");
        helpDesk.setQuery("My first query");

        HelpDesk saved = repository.save(helpDesk);

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

        List<HelpDesk> saved = repository.saveAll(Arrays.asList(helpDesk1, helpDesk2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testSaveShouldAddHelpDeskReply() {
        HelpDesk helpDesk = repository.getOne(MOCK_HELP_DESK_ID_1);

        assertThat(helpDesk.getReply(), equalTo(null));

        helpDesk.setReply("This is a dummy reply.");
        repository.save(helpDesk);

        assertThat(repository.getOne(MOCK_HELP_DESK_ID_1).getReply(), not(emptyString()));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testFindHelpDeskByIdShouldReturnHelpDesk() {
        final Optional<HelpDesk> helpDesk = repository.findById(MOCK_HELP_DESK_ID_1);

        assertThat(helpDesk.isPresent(), equalTo(true));
        assertThat(helpDesk.get().getName(), is("Smith"));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<HelpDesk> helpDeskList = repository.findAll();

        assertThat(helpDeskList.size(), greaterThan(0));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testGetOneShouldReturnHelpDesk() {
        final HelpDesk helpDesk = repository.getOne(MOCK_HELP_DESK_ID_2);

        assertThat(helpDesk.getName(), is("Jack"));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteByIdShouldDeleteHelpDesk() {
        long count = repository.count();

        repository.deleteById(MOCK_HELP_DESK_ID_3);

        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteShouldDeleteHelpDesk() {
        long count = repository.count();

        final HelpDesk helpDesk = repository.getOne(MOCK_HELP_DESK_ID_2);
        repository.delete(helpDesk);

        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/helpdesk/helpdesk-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        repository.deleteAll();

        assertThat(repository.findAll(), hasSize(0));
    }

}
