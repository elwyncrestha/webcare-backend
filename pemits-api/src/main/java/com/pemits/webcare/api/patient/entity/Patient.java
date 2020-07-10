package com.pemits.webcare.api.patient.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.entity.AppUser;
import com.pemits.webcare.core.entity.BaseEntity;
import com.pemits.webcare.core.enums.UserType;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity<Long> implements AppUser {

    @OneToOne
    private User user;

    @NotEmpty(message = "Age is required")
    private int age;

    @Override
    public UserType getUserType() {
        return UserType.PATIENT;
    }
}
