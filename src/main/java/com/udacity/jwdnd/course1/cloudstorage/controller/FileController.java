package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
public class FileController extends DefaultController<File> {

    public FileController(FileService fileService, UserService userService) {
        super(fileService, userService);
    }

    @PostMapping("/files")
    public RedirectView saveFile(@RequestParam("fileUpload") MultipartFile fileUpload, Principal principal, Model model) throws IOException {
        File file = FileService.parseFileUpload(fileUpload);
        return super.save(file, principal, model);
    }

    @GetMapping("/files/{fileId}")
    public RedirectView deleteFile(@PathVariable int fileId) {
        return super.delete(fileId);
    }

    @ResponseBody
    @GetMapping("/files/downloads/{fileId}")
    public void openFile(@PathVariable int fileId, HttpServletResponse response) throws IOException {
        File file = service.getById(fileId);
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=\""+file.getFilename()+"\"");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "-1");

        InputStream stream = new ByteArrayInputStream(file.getFileData());

        response.getOutputStream().write(stream.readAllBytes());
;
    }

}
