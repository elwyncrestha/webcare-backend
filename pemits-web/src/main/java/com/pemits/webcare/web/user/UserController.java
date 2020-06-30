package com.pemits.webcare.web.user;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.service.UserService;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.dto.RestResponseDto;
import com.pemits.webcare.core.enums.Status;
import com.pemits.webcare.web.user.dto.ChangePasswordDto;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@RestController
@RequestMapping(UserController.URL)
@Slf4j
public class UserController extends BaseController<User, Long> {

    static final String URL = "/v1/users";
    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    protected UserController(
        UserService service,
        PasswordEncoder passwordEncoder) {
        super(service, log.getClass());
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> getAuthenticated() {
        return new RestResponseDto().success(service.getAuthenticated());
    }

    @GetMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam Long id, @RequestParam Status status) {
        User user = service.findOne(id).orElse(null);
        if (null == user) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        user.setStatus(status);
        return new RestResponseDto().success(service.save(user));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto) {
        User user = service.findOne(dto.getUserId()).orElse(null);

        if (user == null) {
            return new RestResponseDto()
                .fail(HttpStatus.NOT_FOUND, Optional.of("User not found!!!"));
        } else if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            return new RestResponseDto()
                .fail(HttpStatus.FORBIDDEN, Optional.of("Wrong old password!!!"));
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        User updatedUser = service.save(user);
        if (updatedUser == null) {
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR, Optional.of("Could not update password!!!"));
        }
        return new RestResponseDto().success(dto);
    }
}
