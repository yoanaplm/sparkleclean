package com.sparkleclean.sparkleclean.review.service;

import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.exception.DuplicateReviewException;
import com.sparkleclean.sparkleclean.review.model.Review;
import com.sparkleclean.sparkleclean.review.repository.ReviewRepository;
import com.sparkleclean.sparkleclean.user.service.UserService;
import com.sparkleclean.sparkleclean.web.dto.ReviewDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final CleaningServiceService cleaningServiceService;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, CleaningServiceService cleaningServiceService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.cleaningServiceService = cleaningServiceService;
    }

    @Transactional
    public void create(UUID userId, UUID serviceId, ReviewDto dto){

        if(reviewRepository.existsByUserAndService(userId,serviceId)){
            throw new DuplicateReviewException("You have already reviewed this service");

        }

        Review review = new Review();
        review.setUser(userService.getById(userId));
        review.setCleaningService(cleaningServiceService.getById(serviceId));
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        reviewRepository.save(review);

    }

    public List<Review> getByService(UUID serviceId){
        return reviewRepository.findAllByServiceId(serviceId);
    }
}
