package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CredentialController extends DefaultController<Credential> {

    public CredentialController(CredentialService credentialService, UserService userService) {
        super(credentialService, userService);
    }

    @PostMapping("/credentials")
    public RedirectView saveCredential(Credential credential, Principal principal, Model model) {
        return super.save(credential, principal, model);
    }

    @GetMapping("/credentials/{credentialId}")
    public RedirectView deleteCredential(@PathVariable int credentialId) {
        return super.delete(credentialId);
    }
}
