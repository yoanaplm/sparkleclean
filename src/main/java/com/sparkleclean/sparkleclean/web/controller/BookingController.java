package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.booking.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class BookingController {
    private final BookingService bookingService;



    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;

    }

    @GetMapping("/bookings")
    public ModelAndView getMyBookingsPage(HttpSession session){
        ModelAndView modelAndView= new ModelAndView("my-bookings");
        modelAndView.addObject("bookings", bookingService.getByUser((UUID) session.getAttribute("userId")));
        return modelAndView;
        }

    }

