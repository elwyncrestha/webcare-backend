package com.pemits.webcare.api.appointment.entity;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.core.entity.BaseEntity;
import com.pemits.webcare.core.enums.Gender;
import com.pemits.webcare.core.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseEntity<Long> implements Serializable {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Address is required")
    private String address;
    @NotEmpty(message = "Contact number is required")
    private String contactNumber;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotEmpty(message = "Age is required")
    private int age;
    @NotEmpty(message = "Department is required")
    @OneToOne
    private Department department;
    @NotEmpty(message = "Doctor is required")
    @OneToOne
    private Doctor doctor;
    @NotEmpty(message = "Appoint time is required")
    private LocalDate appointmentDate;
    @NotEmpty(message = "Preferred time is required")
    private LocalDateTime preferredTime;


}
