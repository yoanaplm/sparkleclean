package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.web.dto.BookingDto;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final CleaningServiceService cleaningServiceService;

    public ServiceController(CleaningServiceService cleaningServiceService) {
        this.cleaningServiceService = cleaningServiceService;
    }

    @GetMapping
    public ModelAndView getServicesPage(){
        ModelAndView modelAndView = new ModelAndView("services");
        modelAndView.addObject("services",cleaningServiceService.getAll());
        return modelAndView;


    }
    @GetMapping("/{id}")
    public ModelAndView getServiceDetailsPage(@PathVariable UUID id){
        ModelAndView modelAndView = new ModelAndView("service-details");
        modelAndView.addObject("service",cleaningServiceService.getById(id));
      modelAndView.addObject("bookingDto", new BookingDto());
        return  modelAndView;
    }
}
