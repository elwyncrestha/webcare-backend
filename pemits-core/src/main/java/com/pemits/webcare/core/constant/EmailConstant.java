package com.pemits.webcare.core.constant;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Elvin Shrestha on 6/30/2020
 */
public final class EmailConstant {

    public enum Template {
        REGISTRATION_CREDENTIALS("Registration Credentials!!!"),
        PATIENT_REGISTRATION("Patient Registration Credentials!!!"),
        RESET_PASSWORD("Reset your password!!!"),
        APPOINTMENT_CONFIRMATION("Appointment Confirmation!!!");

        private final String subject;

        Template(String subject) {
            this.subject = subject;
        }

        public String get() {
            return this.subject;
        }
    }

    public static final Map<Template, String> MAIL = ImmutableMap.<Template, String>builder()
        .put(Template.REGISTRATION_CREDENTIALS, "/mail/registration-credentials")
        .put(Template.PATIENT_REGISTRATION, "/mail/patient-registration")
        .put(Template.RESET_PASSWORD, "/mail/reset-password.html")
        .put(Template.APPOINTMENT_CONFIRMATION, "/mail/appointment-confirmation.html")
        .build();

    private EmailConstant() {}
}
