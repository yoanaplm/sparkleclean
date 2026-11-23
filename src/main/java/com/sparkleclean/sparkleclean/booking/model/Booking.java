package com.sparkleclean.sparkleclean.booking.model;

import com.sparkleclean.sparkleclean.cleaningservice.model.CleaningService;
import com.sparkleclean.sparkleclean.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="bookings")
public class Booking {

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
    private LocalDateTime scheduledAt;

    @Column(nullable = false)
    private String address;

    @Column(length = 500)
    private String notes;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

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

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
