package com.pemits.webcare.api.user.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.repository.UserRepository;

public class UserServiceTest {

    private static final String DEFAULT_ADMIN_NAME = "The Administrator";
    private static final long DEFAULT_ADMIN_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindOneGivenIdReturnsUser() {
        final long DEFAULT_ADMIN_ID = 1L;

        when(userRepository.findById(DEFAULT_ADMIN_ID))
            .thenReturn(Optional.of(getDefaultAdminUser()));

        final Optional<User> user = service.findOne(DEFAULT_ADMIN_ID);

        assertThat(user.isPresent(), is(true));
        assertThat(user.get().getName(), is(DEFAULT_ADMIN_NAME));
    }

    private User getDefaultAdminUser() {
        final User admin = new User();
        admin.setId(DEFAULT_ADMIN_ID);
        admin.setName(DEFAULT_ADMIN_NAME);
        return admin;
    }
}
