package com.pemits.webcare.api.user.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
}
