package com.pemits.webcare.web.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.service.UserService;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.dto.RestResponseDto;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@RestController
@RequestMapping(UserController.URL)
@Slf4j
public class UserController extends BaseController<User, Long> {

    static final String URL = "/v1/users";
    private final UserService service;

    protected UserController(
        UserService service
    ) {
        super(service, log.getClass());
        this.service = service;
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> getAuthenticated() {
        return new RestResponseDto().success(service.getAuthenticated());
    }
}
