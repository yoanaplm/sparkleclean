package com.sparkleclean.sparkleclean.booking.repository;

import com.sparkleclean.sparkleclean.booking.model.Booking;
import com.sparkleclean.sparkleclean.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, UUID> {
    @Query("select b from Booking b where b.user.id = :userId order by b.scheduledAt")
    List<Booking> findAllByUserId(@Param("userId") UUID userId);

    UUID user(User user);
}
