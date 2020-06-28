package com.pemits.webcare.api.user.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.user.entity.User;

public class UserRepositoryTest extends BaseJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("/dataset/user/admin-config.xml")
    public void testFindUserByUsernameShouldReturnAdmin() {
        final String DEFAULT_ADMIN_USERNAME = "spadmin";
        final String DEFAULT_ADMIN_NAME = "The Administrator";
        final User user = userRepository.findUserByUsername(DEFAULT_ADMIN_USERNAME);

        assertThat(user.getName(), equalTo(DEFAULT_ADMIN_NAME));
    }
}
