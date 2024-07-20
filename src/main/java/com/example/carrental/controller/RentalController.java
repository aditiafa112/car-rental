package com.example.carrental.controller;

import com.example.carrental.model.Rental;
import com.example.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.findById(id);
    }

    @FunctionalInterface
    interface TotalPriceCalculator {
        Integer calculate(Integer carPrice, Integer days);
    }

    @PostMapping
    public ResponseEntity<?> createRental(@RequestBody Rental rental) {
        try {
            TotalPriceCalculator calculator = (carPrice, days) -> carPrice * days;
            Integer carPrice = rental.getCar().getPrice();
            Integer days = rental.getStartDate().until(rental.getEndDate()).getDays() + 1;
            Integer totalPrice = calculator.calculate(carPrice, days);
            rental.setTotalPrice(totalPrice);
            Rental savedRental = rentalService.save(rental);
            return new ResponseEntity<>(savedRental, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        rental.setId(id);
        return rentalService.save(rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteById(id);
    }
}
