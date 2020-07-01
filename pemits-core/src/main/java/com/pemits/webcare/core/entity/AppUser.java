package com.pemits.webcare.core.entity;

import com.pemits.webcare.core.enums.UserType;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
public interface AppUser {

    default UserType getUserType() {
        return UserType.SUPER_ADMINISTRATOR;
    }

}
