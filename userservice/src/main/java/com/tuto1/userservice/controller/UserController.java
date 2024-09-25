package com.tuto1.userservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuto1.userservice.Model.Bike;
import com.tuto1.userservice.Model.Car;
import com.tuto1.userservice.entity.User;
import com.tuto1.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") int id) {
        User user = userService.getUserByID(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);

        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userID}")
    public ResponseEntity<List<Car>> getcars(@PathVariable("userID") int userID) {
        User user = userService.getUserByID(userID);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userService.getCars(userID);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userID}")
    public ResponseEntity<List<Bike>> getbikes(@PathVariable("userID") int userID) {
        User user = userService.getUserByID(userID);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Bike> cars = userService.getBikes(userID);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/savecar/{userID}")
    public ResponseEntity<Car> saveCar(@PathVariable("userID") int userID, @RequestBody Car car) {
        if (userService.getUserByID(userID) == null) {
            return ResponseEntity.notFound().build();
        }
        Car carNew = userService.save(userID, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userID}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userID") int userID, @RequestBody Bike bike) {
        if (userService.getUserByID(userID) == null) {
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.save(userID, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{userID}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userID") int userID) {
        Map<String, Object> result = userService.getUserandVehicles(userID);
        return ResponseEntity.ok(result);
    }
}