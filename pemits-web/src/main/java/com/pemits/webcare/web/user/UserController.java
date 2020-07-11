package com.pemits.webcare.web.user;

import static com.pemits.webcare.api.user.repository.spec.UserSpec.FILTER_BY_EMAIL;

import java.util.HashMap;
import java.util.Map;
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
import com.pemits.webcare.core.constant.EmailConstant.Template;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.dto.EmailDto;
import com.pemits.webcare.core.dto.RestResponseDto;
import com.pemits.webcare.core.enums.Status;
import com.pemits.webcare.core.service.EmailService;
import com.pemits.webcare.core.utils.PasswordGenerator;
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
    private final EmailService emailService;

    protected UserController(
        UserService service,
        PasswordEncoder passwordEncoder,
        EmailService emailService
    ) {
        super(service, log.getClass());
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
                .fail(HttpStatus.INTERNAL_SERVER_ERROR,
                    Optional.of("Could not update password!!!"));
        }
        return new RestResponseDto().success(dto);
    }

    @PostMapping("/resetPassword/verify")
    public ResponseEntity<?> verifyResetPassword(@RequestBody ChangePasswordDto dto) {
        Map<String, String> filter = new HashMap<>();
        filter.put(FILTER_BY_EMAIL, dto.getEmail());
        Optional<User> user = service.findOneBySpec(filter);

        if (!user.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.of("User not found"));
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .build();
        dto.setToken(passwordGenerator.generate(4));
        dto.setUserId(user.get().getId());

        // send email
        EmailDto emailDto = EmailDto.builder()
            .template(Template.RESET_PASSWORD)
            .to(user.get().getEmail())
            .toName(user.get().getName())
            .resetToken(dto.getToken())
            .build();
        emailService.send(emailDto);

        return new RestResponseDto().success(dto);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDto dto) {
        User user = service.findOne(dto.getUserId()).orElse(null);

        if (user == null) {
            return new RestResponseDto()
                .fail(HttpStatus.NOT_FOUND, Optional.of("User not found!!!"));
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        User updatedUser = service.save(user);
        if (updatedUser == null) {
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR,
                    Optional.of("Could not reset password!!!"));
        }
        return new RestResponseDto().success(dto);
    }
}
