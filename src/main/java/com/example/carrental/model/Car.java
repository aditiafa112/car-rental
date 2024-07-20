package com.example.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car extends Vehicle {

    @Override
    public void  getFuelType() {
        System.out.println("Fuel Type is Electric");
    }
}
