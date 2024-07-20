package com.example.carrental.controller;

import com.example.carrental.model.Rental;
import com.example.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        Integer totalPrice = rental.getCar().getPrice() * (rental.getStartDate().until(rental.getEndDate()).getDays() + 1);
        rental.setTotalPrice(totalPrice);
        return rentalService.save(rental);
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
