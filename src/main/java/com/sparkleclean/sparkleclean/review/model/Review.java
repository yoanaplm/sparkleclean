package com.sparkleclean.sparkleclean.review.model;

import com.sparkleclean.sparkleclean.cleaningservice.model.CleaningService;
import com.sparkleclean.sparkleclean.user.model.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="reviews")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(optional = false)
    @JoinColumn(name = "cleaning_service_id")
    private CleaningService cleaningService;



    @Column(nullable = false)
    private int rating;


    @Column(nullable = false,length = 500)
    private String comment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CleaningService getCleaningService() {
        return cleaningService;
    }

    public void setCleaningService(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
