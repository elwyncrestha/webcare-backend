package com.pemits.webcare.core.dto;

import static com.pemits.webcare.core.constant.AppConstant.FROM_EMAIL;

import lombok.Builder;
import lombok.Data;

import com.pemits.webcare.core.constant.EmailConstant.Template;

/**
 * @author Elvin Shrestha on 6/30/2020
 */
@Builder
@Data
public class EmailDto {

    private final String from = FROM_EMAIL;
    private String to;
    private String toName;
    // credentials field
    private String password;
    private String username;
    private String webUrl;
    private String patientId;

    private Template template;
}
