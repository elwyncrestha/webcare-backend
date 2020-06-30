package com.pemits.webcare.web.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Elvin Shrestha on 6/30/2020
 */
@Getter
@Setter
public class ChangePasswordDto {

    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
