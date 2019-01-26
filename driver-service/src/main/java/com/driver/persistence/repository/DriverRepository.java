package com.driver.persistence.repository;

import com.driver.persistence.entity.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
}
