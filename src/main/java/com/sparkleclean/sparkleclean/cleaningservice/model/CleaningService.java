package com.sparkleclean.sparkleclean.cleaningservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="cleaning_services")
public class CleaningService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

@Column(nullable = false,unique = true)
    private String name;

@Column(nullable = false,length =1000 )
    private String description;

@Column(nullable = false,precision = 10,scale = 2)
private BigDecimal price;

@Column(nullable = false)
private int durationMinutes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
