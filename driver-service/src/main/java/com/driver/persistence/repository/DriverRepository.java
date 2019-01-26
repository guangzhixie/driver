package com.driver.persistence.repository;

import com.driver.persistence.entity.DriverLocationEntity;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface DriverRepository extends CrudRepository<DriverLocationEntity, Integer> {
    @Override
    @NotNull
    List<DriverLocationEntity> findAll();

}
