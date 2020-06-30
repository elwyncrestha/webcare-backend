package com.pemits.webcare.api.inventory.entity;

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
 * created on 6/29/2020
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseEntity<Long> implements Serializable {

    @NotEmpty(message = "Name is required")
    private String name;
    private float price;
    private int quantity;
    private float totalPrice;
    private LocalDate purchaseDate;
}
