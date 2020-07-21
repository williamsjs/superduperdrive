package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService extends DefaultService<Note> {

    public NoteService(NoteMapper noteMapper) {
        super(noteMapper);
    }

    public String getItemName() {
        return "Note";
    }

}
