package org.example.sunnydriveapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Long licencePlate;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String type;
    private String fuel;
    private String transmission;
    private String category;
    private int capacity;
    private Boolean available;
    private Boolean airConditioning;
    private Boolean gps;
    private Float pricePerDay;
    private Float Deposit;
    private String image;
}
