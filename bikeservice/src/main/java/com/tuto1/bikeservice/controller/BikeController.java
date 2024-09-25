package com.tuto1.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuto1.bikeservice.entity.Bike;
import com.tuto1.bikeservice.service.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService BikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> Bikes = BikeService.getAll();

        if (Bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(Bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeByID(@PathVariable("id") int id) {
        Bike Bike = BikeService.getBikeByID(id);

        if (Bike == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike Bike) {
        Bike BikeNew = BikeService.save(Bike);

        return ResponseEntity.ok(BikeNew);
    }

    @GetMapping("/byuser/{userID}")
    public ResponseEntity<List<Bike>> getByUserID(@PathVariable("userID") int userID) {
        List<Bike> Bikes = BikeService.byUserId(userID);

        return ResponseEntity.ok(Bikes);
    }
}
