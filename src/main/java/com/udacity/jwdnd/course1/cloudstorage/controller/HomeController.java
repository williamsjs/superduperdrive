package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private CredentialService credentialService;

    private UserService userService;

    public HomeController(NoteService noteService, CredentialService credentialService, UserService userService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping
    public String get(Principal principal, Model model, Note note) {
        int userId = userService.getByUsername(principal.getName()).getId();

        model.addAttribute("notes", noteService.getByUserId(userId));
        model.addAttribute("credentials", credentialService.getByUserId(userId));

        return "home";
    }

}
