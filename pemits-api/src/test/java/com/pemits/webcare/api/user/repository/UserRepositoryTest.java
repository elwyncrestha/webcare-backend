package com.pemits.webcare.api.user.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.enums.UserType;

public class UserRepositoryTest extends BaseJpaTest {

    private static final long DEFAULT_ADMIN_ID = 1L;
    private static final String DEFAULT_ADMIN_USERNAME = "spadmin";
    private static final String DEFAULT_ADMIN_NAME = "The Administrator";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveShouldReturnSavedUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@mail.com");
        user.setUsername("johndoe");
        user.setUserType(UserType.ADMINISTRATOR);
        user.setPassword("123456");

        User saved = userRepository.save(user);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    public void testSaveAllShouldReturnAllSavedUser() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@mail.com");
        user1.setUsername("johndoe");
        user1.setUserType(UserType.ADMINISTRATOR);
        user1.setPassword("123456");

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail("jane@mail.com");
        user2.setUsername("janedoe");
        user2.setUserType(UserType.ADMINISTRATOR);
        user2.setPassword("987654");

        List<User> saved = userRepository.saveAll(Arrays.asList(user1, user2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testFindUserByUsernameShouldReturnAdmin() {
        final User user = userRepository.findUserByUsername(DEFAULT_ADMIN_USERNAME);

        assertThat(user.getName(), equalTo(DEFAULT_ADMIN_NAME));
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<User> users = userRepository.findAll();

        assertThat(users.size(), equalTo(1));
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testGetOneShouldReturnUserOfTypeAdmin() {
        final User user = userRepository.getOne(DEFAULT_ADMIN_ID);

        assertThat(user.getUserType(), is(UserType.SUPER_ADMINISTRATOR));
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testDeleteByIdShouldDeleteAdmin() {
        userRepository.deleteById(DEFAULT_ADMIN_ID);

        assertThat(userRepository.findAll(), hasSize(0));
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testDeleteShouldDeleteAdmin() {
        final User user = userRepository.getOne(DEFAULT_ADMIN_ID);

        userRepository.delete(user);

        assertThat(userRepository.findAll(), hasSize(0));
    }

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        userRepository.deleteAll();

        assertThat(userRepository.findAll(), hasSize(0));
    }
}
