package com.sparkleclean.sparkleclean.web.dto;

import jakarta.validation.constraints.*;

public class ReviewDto {


    @NotNull(message = "Rating required")
    @Min(value=1,message = "Rating must be between 1 and 5")
    @Max(value=5,message = "Rating must be between 1 and 5")
    private Integer rating;


    @NotBlank(message = "Comment required")
    @Size(min=5,max=500,message = "Must be between 5 and 500 characters")
    private String comment;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
