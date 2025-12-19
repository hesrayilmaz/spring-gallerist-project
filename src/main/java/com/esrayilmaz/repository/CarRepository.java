package com.esrayilmaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esrayilmaz.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
