package com.sparkleclean.sparkleclean.web.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CleaningServiceDto {
    @NotBlank(message="Required")
    @Size(min=3,max=50,message ="Must be between 3 and 50 characters")
    private String name;


    @NotBlank(message="Required")
    @Size(min=10,max=500,message ="Must be between 10 and 500 characters")
    private String description;


    @NotNull(message="Required")
    @Positive(message = "Must be greater than zero")
    private BigDecimal price;

    @NotNull(message="Required")
    @Min(value=30,message="Duration must be at least 30 minutes")
    @Max(value=480,message = "Maximum duration is 8 hours ")
    private Integer durationMinutes;

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

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
