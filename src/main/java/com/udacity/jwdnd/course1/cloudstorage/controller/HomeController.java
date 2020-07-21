package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private CredentialService credentialService;

    private FileService fileService;

    private UserService userService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService, UserService userService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    public String get(@RequestParam(required=false) String message, Principal principal, ModelMap modelMap, Note note, Credential credential) {
        int userId = userService.getByUsername(principal.getName()).getId();

        modelMap.addAttribute("notes", noteService.getByUserId(userId));
        modelMap.addAttribute("credentials", credentialService.getByUserId(userId));
        modelMap.addAttribute("files", fileService.getByUserId(userId));

        if (message != null) {
            if (message.toLowerCase().contains("error")) {
                modelMap.addAttribute("error", message);
            } else {
                modelMap.addAttribute("success", message);
            }
        }

        return "home";
    }

}
