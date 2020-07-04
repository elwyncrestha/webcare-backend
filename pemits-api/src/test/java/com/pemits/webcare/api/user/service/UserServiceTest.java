package com.pemits.webcare.api.user.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.repository.UserRepository;
import com.pemits.webcare.core.enums.UserType;
import com.pemits.webcare.core.service.EmailService;

public class UserServiceTest {

    private static final String DEFAULT_ADMIN_NAME = "The Administrator";
    private static final long DEFAULT_ADMIN_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveShouldReturnSavedUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@mail.com");
        user.setUsername("johndoe");
        user.setUserType(UserType.ADMINISTRATOR);
        user.setPassword("123456");

        User savedStub = new User();
        BeanUtils.copyProperties(user, savedStub);
        savedStub.setId(1L);

        when(userRepository.save(user)).thenReturn(savedStub);

        User saved = service.save(user);

        assertThat(saved.getId(), equalTo(savedStub.getId()));
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

        User user1Stub = new User();
        User user2Stub = new User();

        BeanUtils.copyProperties(user1, user1Stub);
        user1Stub.setId(1L);
        BeanUtils.copyProperties(user1, user2Stub);
        user2Stub.setId(2L);

        List<User> users = Arrays.asList(user1, user2);
        List<User> usersStud = Arrays.asList(user1Stub, user2Stub);

        when(userRepository.saveAll(users)).thenReturn(usersStud);

        List<User> saved = service.saveAll(users);

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
        assertThat(saved.get(0).getId(), is(1L));
        assertThat(saved.get(1).getId(), is(2L));
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
