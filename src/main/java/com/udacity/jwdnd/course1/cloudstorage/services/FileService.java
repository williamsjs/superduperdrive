package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService extends DefaultService<File> {

    public FileService(FileMapper mapper) {
        super(mapper);
    }

    public static File parseFileUpload(MultipartFile fileUpload) throws IOException {
        File file = new File();
        file.setFilename(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        file.setFileSize(fileUpload.getSize());
        InputStream stream = fileUpload.getInputStream();
        file.setFileData(IOUtils.toByteArray(stream));

        return file;
    }

    @Override
    public String save(File file, Integer userId) {
        List<File> files = super.getByUserId(userId);

        for (File downloadedFile : files) {
            if (file.getFilename().equals(downloadedFile.getFilename())) {
                return downloadedFile.getFilename() + " already exists.  Please choose another filename";
            }
        }

        super.save(file, userId);
        return null;

    }

}
