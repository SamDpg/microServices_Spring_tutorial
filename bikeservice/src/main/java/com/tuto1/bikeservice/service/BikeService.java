package com.tuto1.bikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuto1.bikeservice.entity.Bike;
import com.tuto1.bikeservice.repository.BikeRepository;

@Service
@Transactional
public class BikeService {
    @Autowired
    BikeRepository BikeRepository;

    public List<Bike> getAll() {
        return BikeRepository.findAll();
    }

    public Bike getBikeByID(int id) {
        return BikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike Bike) {
        Bike BikeNew = BikeRepository.save(Bike);

        return BikeNew;
    }

    public List<Bike> byUserId(int userID) {
        return BikeRepository.findByUserID(userID);
    }
}
