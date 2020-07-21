package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.DefaultModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.DefaultService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
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

    protected RedirectView save(T t, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        String message = service.save(t, user.getId());

        if (message != null) {
            return new RedirectView("/home?message=" + message);
        }

        return new RedirectView("/home");
    }

    protected RedirectView delete(int id) {
        String message = service.delete(id);
        return new RedirectView("/home?message=" + message);
    }
}
