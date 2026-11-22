package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.cleaningservice.service.CleaningServiceService;
import com.sparkleclean.sparkleclean.web.dto.CleaningServiceDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/admin/services")
public class AdminServiceController {
    private final CleaningServiceService cleaningServiceService;

    public AdminServiceController(CleaningServiceService cleaningServiceService) {
        this.cleaningServiceService = cleaningServiceService;
    }

    @GetMapping
    public ModelAndView getServicesPage(){
        ModelAndView modelAndView = new ModelAndView("admin-services");
        modelAndView.addObject("services",cleaningServiceService.getAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreatePage(){
        ModelAndView modelAndView = formView("Add a service","/admin/services/create");
        modelAndView.addObject("serviceDto",new CleaningServiceDto());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("serviceDto") CleaningServiceDto serviceDto, BindingResult bindingResult){
        ModelAndView error=formView("Add a service","/admin/services/create");

        if(bindingResult.hasErrors()){
            return  error;
        }
        try{
            cleaningServiceService.create(serviceDto);
        } catch (RuntimeException exception) {
            bindingResult.reject("service.create.failed", exception.getMessage());
            return error;
        }
        return new ModelAndView("redirect:/admin/services");
    }
@GetMapping("/{id}/edit")
    public ModelAndView getEditPage(@PathVariable UUID id){
        ModelAndView modelAndView = formView("Edit service", "/admin/services/" + id + "/edit");
        modelAndView.addObject("serviceDto",cleaningServiceService.getDtoById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable UUID id,@Valid @ModelAttribute("serviceDto") CleaningServiceDto serviceDto,BindingResult bindingResult) {
        ModelAndView error = formView("Edit service", "/admin/services/" + id + "/edit");
        if (bindingResult.hasErrors()) {
            return error;
        }
        try {
            cleaningServiceService.update(id,serviceDto);

        }catch (RuntimeException exception){
            bindingResult.reject("service.update.failed",exception.getMessage());
            return error;
        }
        return new ModelAndView("redirect:/admin/services");

}
@PostMapping("/{id}/delete")
public ModelAndView delete(@PathVariable UUID id){
    cleaningServiceService.delete(id);
    return  new ModelAndView("redirect:/admin/services");


    }
    private ModelAndView formView(String title,String formAction){
        ModelAndView modelAndView = new ModelAndView("service-form");
        modelAndView.addObject("title",title);
        modelAndView.addObject("formAction",formAction);
        return modelAndView;
    }
}
