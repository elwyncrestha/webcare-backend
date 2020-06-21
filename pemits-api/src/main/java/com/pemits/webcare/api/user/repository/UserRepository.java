package com.pemits.webcare.api.user.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    User findUserByUsername(String username);
}
