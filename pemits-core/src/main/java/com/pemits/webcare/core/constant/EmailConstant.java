package com.pemits.webcare.core.constant;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Elvin Shrestha on 6/30/2020
 */
public final class EmailConstant {

    public enum Template {
        REGISTRATION_CREDENTIALS("Registration Credentials!!!");

        private String subject;

        Template(String subject) {
            this.subject = subject;
        }

        public String get() {
            return this.subject;
        }
    }

    public static final Map<Template, String> MAIL = ImmutableMap.<Template, String>builder()
        .put(Template.REGISTRATION_CREDENTIALS, "/mail/registration-credentials")
        .build();

    private EmailConstant() {}
}
