package com.sparkleclean.sparkleclean.review.repository;

import com.sparkleclean.sparkleclean.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query("select r from Review r where r.cleaningService.id = :serviceId")
    List<Review> findAllByServiceId(@Param("serviceId") UUID serviceId);

    @Query("select count(r) > 0 from Review r where r.user.id = :userId and r.cleaningService.id = :serviceId")
    boolean existsByUserAndService(@Param("userId") UUID userId, @Param("serviceId") UUID serviceId);


    @Query("select count(r) > 0 from Review r where r.cleaningService.id = :serviceId")
    boolean existsByServiceId(@Param("serviceId") UUID serviceId);

}
