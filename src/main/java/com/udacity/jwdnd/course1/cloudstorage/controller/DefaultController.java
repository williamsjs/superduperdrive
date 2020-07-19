package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.DefaultModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.DefaultService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public abstract class DefaultController<T extends DefaultModel> {

    protected DefaultService<T> service;

    protected UserService userService;

    public DefaultController(DefaultService<T> service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    protected RedirectView save(T t, Principal principal, Model model) {
        User user = userService.getByUsername(principal.getName());
        String error = service.save(t, user.getId());

        if (error != null) {
            return new RedirectView("/home?error=" + error);
        }

        return new RedirectView("/home");
    }

    protected RedirectView delete(int id) {
        service.delete(id);
        return new RedirectView("/home");
    }
}
