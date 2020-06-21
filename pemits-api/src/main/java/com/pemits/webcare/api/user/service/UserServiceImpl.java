package com.pemits.webcare.api.user.service;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.repository.UserRepository;
import com.pemits.webcare.api.user.repository.spec.UserSpecBuilder;
import com.pemits.webcare.core.enums.Status;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    protected UserServiceImpl(
        UserRepository repository,
        PasswordEncoder passwordEncoder) {
        super(repository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(Status.ACTIVE);
        } else {
            user.setPassword(repository.getOne(user.getId()).getPassword());
        }
        return repository.save(user);
    }

    @Override
    protected BaseSpecBuilder<User> getSpec(Map<String, String> filterParams) {
        return new UserSpecBuilder(filterParams);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            user = this.findByUsername(user.getUsername());
            return user;
        } else {
            throw new UsernameNotFoundException(
                "User is not authenticated; Found of type " + authentication.getPrincipal()
                    .getClass() + "; Expected type User");
        }
    }
}
