package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView get(@RequestParam(required = false, name = "signupSuccess") boolean signupSuccess, ModelMap modelMap) {

        if (signupSuccess) {
            modelMap.addAttribute("signupSuccess", true);
        }

        return new ModelAndView("/login", modelMap);
    }

}
