package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.booking.model.BookingStatus;
import com.sparkleclean.sparkleclean.booking.service.BookingService;
import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.exception.ForbiddenActionException;
import com.sparkleclean.sparkleclean.exception.InvalidBookingTimeException;
import com.sparkleclean.sparkleclean.user.model.UserRole;
import com.sparkleclean.sparkleclean.web.dto.BookingDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final CleaningServiceService cleaningServiceService;


    public BookingController(BookingService bookingService, CleaningServiceService cleaningServiceService) {
        this.bookingService = bookingService;
        this.cleaningServiceService = cleaningServiceService;
    }

    @PostMapping("/services/{id}/book")
    public ModelAndView book (@PathVariable UUID id, @Valid @ModelAttribute("bookingDto")BookingDto bookingDto, BindingResult bindingResult, HttpSession session){
        if(session.getAttribute("role") != UserRole.CLIENT){
            throw new ForbiddenActionException("Only clients can book");

        }

        ModelAndView error  = new ModelAndView("service-details");
        error.addObject("service",cleaningServiceService.getById(id));
        if (bindingResult.hasErrors()){
            return error;
        }
        try {
            bookingService.create((UUID) session.getAttribute("userId"),id, bookingDto);

        } catch ( InvalidBookingTimeException e) {
            bindingResult.reject("booking.invalid", e.getMessage());
            return  error;
        }
        return new ModelAndView("redirect:/bookings");


    }
    @GetMapping("/bookings")
    public ModelAndView getMyBookingsPage(HttpSession session){
        ModelAndView modelAndView= new ModelAndView("my-bookings");
        modelAndView.addObject("bookings", bookingService.getByUser((UUID) session.getAttribute("userId")));
        return modelAndView;
        }

    }

