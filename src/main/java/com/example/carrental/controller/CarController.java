package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return carService.save(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
