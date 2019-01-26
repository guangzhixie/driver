package com.driver.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "driver")
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String driverName;
    private String mobileNumber;
}
