package com.pemits.webcare.api.appointment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.pemits.webcare.api.department.entity.Department;
import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.core.entity.BaseEntity;

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

    @OneToOne
    private Patient patient;

    @NotEmpty(message = "Department is required")
    @OneToOne
    private Department department;

    @NotEmpty(message = "Doctor is required")
    @OneToOne
    private Doctor doctor;

    @NotEmpty(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotEmpty(message = "Appointment time is required")
    private LocalTime appointmentTime;

}
