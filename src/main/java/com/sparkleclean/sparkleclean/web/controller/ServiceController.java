package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.booking.service.BookingService;
import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.exception.DuplicateReviewException;
import com.sparkleclean.sparkleclean.exception.ForbiddenActionException;
import com.sparkleclean.sparkleclean.exception.InvalidBookingTimeException;
import com.sparkleclean.sparkleclean.review.service.ReviewService;
import com.sparkleclean.sparkleclean.user.model.UserRole;
import com.sparkleclean.sparkleclean.web.dto.BookingDto;
import com.sparkleclean.sparkleclean.web.dto.ReviewDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/services")
public class ServiceController {


    private final CleaningServiceService cleaningServiceService;
    private final BookingService bookingService;
    private final ReviewService reviewService;

    public ServiceController(CleaningServiceService cleaningServiceService, BookingService bookingService, ReviewService reviewService) {
        this.cleaningServiceService = cleaningServiceService;
        this.bookingService = bookingService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ModelAndView getServicesPage(){
        ModelAndView modelAndView = new ModelAndView("services");
        modelAndView.addObject("services",cleaningServiceService.getAll());
        return modelAndView;


    }
    @GetMapping("/{id}")
    public ModelAndView getServiceDetailsPage(@PathVariable UUID id){
        ModelAndView modelAndView = detailsView(id);
        modelAndView.addObject("bookingDto",new BookingDto());
      modelAndView.addObject("reviewDto", new ReviewDto());
        return  modelAndView;
    }
    @PostMapping("/{id}/book")
    public ModelAndView book(@PathVariable UUID id, @Valid @ModelAttribute("bookingDto") BookingDto bookingDto, BindingResult bindingResult, HttpSession session){
        requireClient(session);
        ModelAndView errorView = detailsView(id);
        errorView.addObject("reviewDto", new ReviewDto() );

        if(bindingResult.hasErrors()){
            return errorView;
        }

        try{

            bookingService.create((UUID) session.getAttribute("userId"),id,bookingDto);
        }catch (InvalidBookingTimeException exception){
            bindingResult.reject("booking.invalid", exception.getMessage());
            return errorView;
        }
        return new ModelAndView("redirect:/bookings");

    }

    @PostMapping("/{id}/review")
    public ModelAndView review(@PathVariable UUID id, @Valid @ModelAttribute("reviewDto") ReviewDto reviewDto, BindingResult bindingResult,HttpSession session){
        requireClient(session);
        ModelAndView errorView = detailsView(id);
        errorView.addObject("bookingDto", new BookingDto());

        if (bindingResult.hasErrors()){
            return errorView;
        }
        try {
            reviewService.create((UUID) session.getAttribute("userId"), id, reviewDto);
        } catch (DuplicateReviewException exception) {
            bindingResult.reject("review.duplicate", exception.getMessage());
            return errorView;
        }
        return new ModelAndView(("redirect:/services/" +id));

    }

    private ModelAndView detailsView(UUID id){
        ModelAndView modelAndView = new ModelAndView("service-details");
        modelAndView.addObject("service",cleaningServiceService.getById(id));
        modelAndView.addObject("reviews",reviewService.getByService(id));

        return modelAndView;
    }

    private void requireClient(HttpSession session){

        if(session.getAttribute("role" ) != UserRole.CLIENT){
            throw new ForbiddenActionException("Only clients can book and review services");
        }
    }


}
