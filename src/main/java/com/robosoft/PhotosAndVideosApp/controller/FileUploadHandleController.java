package com.robosoft.PhotosAndVideosApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robosoft.PhotosAndVideosApp.model.FileStorageDirectory;
import com.robosoft.PhotosAndVideosApp.model.UploadFileResponse;
import com.robosoft.PhotosAndVideosApp.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/file")
public class FileUploadHandleController {


    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/uploadFiles",method=RequestMethod.POST)
    public UploadFileResponse uploadFiles(@RequestParam("file") MultipartFile file,
                                          @RequestParam  String fileStorage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        FileStorageDirectory fileStorageDirectory=new FileStorageDirectory() ;
        try {
            fileStorageDirectory = mapper.readValue(fileStorage, FileStorageDirectory.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String fileName = fileStorageService.storeFiles(file,fileStorageDirectory);

        return new UploadFileResponse(fileName,
                file.getContentType(), file.getSize());
    }

}
