package com.pemits.webcare.api.user.service;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.entity.AppUser;
import com.pemits.webcare.core.service.BaseService;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public interface UserService extends BaseService<User, Long> {

    User findByUsername(String username);

    User getAuthenticated();

    AppUser saveAppUser(AppUser appUser);

}
