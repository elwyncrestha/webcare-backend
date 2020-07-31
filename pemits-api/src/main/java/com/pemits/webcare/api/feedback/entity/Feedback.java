package com.pemits.webcare.api.feedback.entity;

import com.pemits.webcare.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Feedback extends BaseEntity<Long> {

    private String fullName;
    private String phoneNo;
    private String email;
    private String message;
}
