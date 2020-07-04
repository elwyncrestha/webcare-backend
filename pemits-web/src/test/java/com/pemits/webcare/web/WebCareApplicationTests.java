package com.pemits.webcare.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.service.UserServiceImpl;
import com.pemits.webcare.web.user.UserController;
import com.pemits.webcare.web.user.dto.ChangePasswordDto;

@ExtendWith(MockitoExtension.class)
class WebCareApplicationTests {

    @Mock
    private PasswordEncoder passwordEncoder;
    private MockMvc mockMvc;
    @Mock
    private UserServiceImpl service;
    @InjectMocks
    private UserController userController;

    // initialized by `JacksonTester.initFields()`
    private JacksonTester<ChangePasswordDto> changePasswordDtoJacksonTester;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testChangePassword() throws Exception {
        final String oldPassword = "123456";
        final String newPassword = "12345678";
        final String oldPasswordHash = "abcdef";
        final String newPasswordHash = "qwerty";
        final long mockUserId = 1L;

        ChangePasswordDto dto = new ChangePasswordDto();
        dto.setUserId(mockUserId);
        dto.setOldPassword(oldPassword);
        dto.setNewPassword(newPassword);

        // password encoder stubs
        when(passwordEncoder.encode(oldPassword)).thenReturn(oldPasswordHash);
        when(passwordEncoder.encode(newPassword)).thenReturn(newPasswordHash);

        // not updated user mock
        User user = new User();
        user.setId(mockUserId);
        user.setPassword(passwordEncoder.encode(dto.getOldPassword()));

        // update user mock
        User saved = new User();
        BeanUtils.copyProperties(user, saved);
        saved.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        // stubs
        when(passwordEncoder.matches(dto.getOldPassword(), user.getPassword())).thenReturn(true);
        when(service.findOne(any(Long.class))).thenReturn(Optional.of(user));
        when(service.save(any(User.class))).thenReturn(saved);

        MockHttpServletResponse response = mockMvc.perform(
            post("/v1/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(changePasswordDtoJacksonTester.write(dto).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    }

}
