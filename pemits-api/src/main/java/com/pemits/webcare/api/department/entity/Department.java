package com.pemits.webcare.api.department.entity;

import com.pemits.webcare.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity<Long> implements Serializable {
    private String name;
}
