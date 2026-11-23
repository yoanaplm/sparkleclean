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

    @Query("select count(b) > 0 from Booking b where b.cleaningService.id = :serviceId")
    boolean existsByServiceId(@Param("serviceId") UUID serviceId);
    
    UUID user(User user);
}
