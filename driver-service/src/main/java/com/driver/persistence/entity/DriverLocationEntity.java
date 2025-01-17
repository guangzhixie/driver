package com.driver.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverLocationEntity {
    @Id
    private Integer id;

    private Double latitude, longitude;
    private Float accuracy;
    private LocalDateTime gmt_create, gmt_modified;
}
