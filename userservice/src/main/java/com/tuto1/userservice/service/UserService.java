package com.tuto1.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tuto1.userservice.Model.Bike;
import com.tuto1.userservice.Model.Car;
import com.tuto1.userservice.entity.User;
import com.tuto1.userservice.feignclients.BikeFeignClient;
import com.tuto1.userservice.feignclients.CarFeignClient;
import com.tuto1.userservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserByID(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);

        return userNew;
    }

    @SuppressWarnings(value = "unchecked")
    public List<Car> getCars(int userID) {
        List<Car> cars = restTemplate.getForObject("http://localhost/car/byuser/" + userID, List.class);

        return cars;
    }

    // Debo de mirar qué ocurre con esto, para qué sirve y además quea parte he
    // perdido: Type safety: The expression of type List needs unchecked conversion
    // to conform to List<Car>
    @SuppressWarnings(value = "unchecked")
    public List<Bike> getBikes(int userID) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost/bike/byuser/" + userID, List.class);

        return bikes;
    }

    public Car save(int id, Car car) {
        car.setUserID(id);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike save(int id, Bike bike) {
        bike.setUserID(id);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserandVehicles(int userID) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userID).orElse(null);

        if (user == null) {
            result.put("Mensaje", "No existe user.");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userID);
        if (cars.isEmpty()) {
            result.put("Cars", "ese user no tiene coches");
        } else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userID);
        if (bikes.isEmpty()) {
            result.put("Bikes", "ese user no tiene motos");
        } else {
            result.put("Bikes", bikes);
        }

        return result;
    }
}
