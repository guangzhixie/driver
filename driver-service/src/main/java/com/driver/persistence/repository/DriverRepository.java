package com.driver.persistence.repository;

import com.driver.persistence.entity.DriverLocationEntity;
import org.springframework.data.repository.CrudRepository;


public interface DriverRepository extends CrudRepository<DriverLocationEntity, Integer> {

}
