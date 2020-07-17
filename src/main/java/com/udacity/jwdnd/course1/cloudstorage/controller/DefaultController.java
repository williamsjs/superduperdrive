package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.DefaultModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.DefaultService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    protected RedirectView insert(T t, Principal principal) {
        User user = userService.getByUsername(principal.getName());

        if (t.getId() == 0) {
            t.setUserId(user.getId());
            service.add(t);
        } else {
            service.update(t);
        }

        return new RedirectView("/home");
    }

    protected RedirectView delete(@PathVariable int id) {
        service.delete(id);
        return new RedirectView("/home");
    }
}
