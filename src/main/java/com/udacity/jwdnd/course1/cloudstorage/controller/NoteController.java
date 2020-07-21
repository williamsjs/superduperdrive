package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class NoteController extends DefaultController<Note> {

    public NoteController(NoteService noteService, UserService userService) {
        super(noteService, userService);
    }

    @PostMapping("/notes")
    public RedirectView saveNote(Note note, Principal principal) {
        return super.save(note, principal);
    }

    @GetMapping("/notes/{noteId}")
    public RedirectView deleteNote(@PathVariable int noteId) {
        return super.delete(noteId);
    }

}
