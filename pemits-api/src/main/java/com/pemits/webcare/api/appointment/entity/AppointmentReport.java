package com.pemits.webcare.api.appointment.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.pemits.webcare.core.entity.BaseEntity;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppointmentReport extends BaseEntity<Long> {

    @OneToOne
    private Appointment appointment;

    private String data;
}
