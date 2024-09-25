package com.tuto1.carservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuto1.carservice.entity.Car;
import com.tuto1.carservice.repository.CarRepository;

@Service
@Transactional
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCarByID(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car) {
        Car carNew = carRepository.save(car);

        return carNew;
    }

    public List<Car> byUserId(int userID) {
        return carRepository.findByUserID(userID);
    }
}
