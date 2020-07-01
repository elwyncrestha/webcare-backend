package com.pemits.webcare.api.doctor.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.entity.AppUser;
import com.pemits.webcare.core.entity.BaseEntity;
import com.pemits.webcare.core.enums.UserType;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Doctor extends BaseEntity<Long> implements AppUser {

    @OneToOne
    private User user;

    @OneToOne
    private Department department;

    private String specializationField;

    @Override
    public UserType getUserType() {
        return UserType.DOCTOR;
    }
}
