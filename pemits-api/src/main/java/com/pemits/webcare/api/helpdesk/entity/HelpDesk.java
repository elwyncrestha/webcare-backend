package com.pemits.webcare.api.helpdesk.entity;

import com.pemits.webcare.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HelpDesk extends BaseEntity<Long> implements Serializable {

    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Contact number is required")
    private String contactNumber;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Query is required")
    private String query;
    private LocalDate queryDate = LocalDate.now();
}
