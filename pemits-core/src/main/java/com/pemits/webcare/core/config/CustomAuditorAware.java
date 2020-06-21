package com.pemits.webcare.core.config;

import static com.pemits.webcare.core.constant.AppConstant.USERNAME;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pemits.webcare.core.dao.UserDao;
import com.pemits.webcare.core.exception.InvalidTokenException;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Component
public class CustomAuditorAware implements AuditorAware<Long> {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ObjectMapper objectMapper = new ObjectMapper();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        Map<String, Object> map = objectMapper
            .convertValue(authentication.getPrincipal(), Map.class);
        if (!map.isEmpty()) {
            Long id = userDao.getAuthenticatedUserId(map.get(USERNAME).toString());
            return Optional.of(id);
        }

        throw new InvalidTokenException();
    }
}
