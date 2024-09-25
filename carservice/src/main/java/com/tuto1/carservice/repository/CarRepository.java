package com.tuto1.carservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuto1.carservice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByUserID(int id);
}
