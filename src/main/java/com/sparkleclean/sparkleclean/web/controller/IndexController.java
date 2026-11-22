package com.sparkleclean.sparkleclean.web.controller;

import com.sparkleclean.sparkleclean.user.service.UserService;
import com.sparkleclean.sparkleclean.web.dto.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(Model model) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerDto",new UserRegisterDto());
        return modelAndView;
    }

    @PostMapping("/register")
        public ModelAndView register (@Valid @ModelAttribute("registerDto") UserRegisterDto registerDto, BindingResult
        bindingResult) {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("register");
            }
            try {

                userService.register(registerDto);

            } catch (RuntimeException exception) {
                bindingResult.reject("registration.failed", exception.getMessage());
                return new ModelAndView("register");
            }
            return new ModelAndView("redirect:/login");
        }

    }


