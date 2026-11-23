package com.sparkleclean.sparkleclean.booking.repository;

import com.sparkleclean.sparkleclean.booking.model.Booking;
import com.sparkleclean.sparkleclean.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, UUID> {
    List<Booking> findUserIdOrderByScheduledAtAsc (UUID userId);

    UUID user(User user);
}
