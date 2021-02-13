package com.robosoft.PhotosAndVideosApp.service;


import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import com.robosoft.PhotosAndVideosApp.constants.AppConstants;
import com.robosoft.PhotosAndVideosApp.entity.Photos;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import com.robosoft.PhotosAndVideosApp.model.FileStorageDirectory;
import com.robosoft.PhotosAndVideosApp.model.PhotoResponse;
import com.robosoft.PhotosAndVideosApp.repository.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {


    @Value("${create.dir}")
    private String directorys;

   @Autowired
   PhotoService   photoService;

   @Autowired
    PhotoRepository  photoRepository;
    @Autowired
    private AsyncronasTaskHandling asyncronasTaskHandling;
    public String storeFiles(MultipartFile file, FileStorageDirectory fileStorageDirectory) {

        return imageSavings(file,fileStorageDirectory);
    }



    private String imageSavings(MultipartFile file,FileStorageDirectory fileStorageDirectory){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Long photographerId=fileStorageDirectory.getPhotographerId();
        Long PhotoId=fileStorageDirectory.getPhotoId();
        MediaType mediaType=fileStorageDirectory.getMediaType();
String[] listOfSize= {"SMALL","MEDIUM","LARGE"};
        for (String size:listOfSize
             ) {
            if(size.equals("SMALL")){
                String storeDirectory=null;
                try {
                    // Check if the file's name contains invalid characters
                    if(fileName.contains("..")) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.");
                        // throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                    }

                    storeDirectory=String.format(directorys,mediaType.toString(),
                            photographerId,
                            PhotoId,size);
                    Path finalPath= Paths.get(storeDirectory)
                            .toAbsolutePath().normalize();
                    try {
                        Files.createDirectories(finalPath);
                    } catch (Exception ex) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
                        //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
                    }


                    // Copy file to the target location (Replacing existing file with the same name)
                    Path targetLocationn = finalPath.resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocationn, StandardCopyOption.REPLACE_EXISTING);

                    String path= AppConstants.IMAGE_BASE_URL+mediaType.toString()+"/"+
                            photographerId+"/"+
                            PhotoId+"/"+size+"/"+fileName;

                   Photos pot= new Photos();
                   Optional<Photos> photo=photoRepository.findById(PhotoId);
                    photo.get().setUrlS(path);
                    photoRepository.save(pot);;

                } catch (IOException ex) {
                    throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
                    // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
                }

                System.out.println("Invoking an asynchronous method. "
                        + Thread.currentThread().getName());
                // file compression in asyncronass manner
                asyncronasTaskHandling.imageCompression100X100(storeDirectory,fileName);


        }
            else if((size.equals("MEDIUM"))){
                String storeDirectory=null;
                try {
                    // Check if the file's name contains invalid characters
                    if(fileName.contains("..")) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.");
                        // throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                    }

                    storeDirectory=String.format(directorys,mediaType.toString(),
                            photographerId,
                            PhotoId,size);
                    Path finalPath= Paths.get(storeDirectory)
                            .toAbsolutePath().normalize();
                    try {
                        Files.createDirectories(finalPath);
                    } catch (Exception ex) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
                        //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
                    }


                    // Copy file to the target location (Replacing existing file with the same name)
                    Path targetLocationn = finalPath.resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocationn, StandardCopyOption.REPLACE_EXISTING);

                    String path= AppConstants.IMAGE_BASE_URL+mediaType.toString()+"/"+
                            photographerId+"/"+
                            PhotoId+"/"+size+"/"+fileName;

                    Photos pot= new Photos();
                    Optional<Photos> photo=photoRepository.findById(PhotoId);
                    photo.get().setUrlS(path);
                    photoRepository.save(pot);;

                } catch (IOException ex) {
                    throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
                    // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
                }

                System.out.println("Invoking an asynchronous method. "
                        + Thread.currentThread().getName());
                // file compression in asyncronass manner
                asyncronasTaskHandling.imageCompression200X200(storeDirectory,fileName);
            }
            else {
                String storeDirectory=null;
                try {
                    // Check if the file's name contains invalid characters
                    if(fileName.contains("..")) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.");
                        // throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                    }

                    storeDirectory=String.format(directorys,mediaType.toString(),
                            photographerId,
                            PhotoId,size);
                    Path finalPath= Paths.get(storeDirectory)
                            .toAbsolutePath().normalize();
                    try {
                        Files.createDirectories(finalPath);
                    } catch (Exception ex) {
                        throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
                        //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
                    }


                    // Copy file to the target location (Replacing existing file with the same name)
                    Path targetLocationn = finalPath.resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocationn, StandardCopyOption.REPLACE_EXISTING);

                    String path= AppConstants.IMAGE_BASE_URL+mediaType.toString()+"/"+
                            photographerId+"/"+
                            PhotoId+"/"+size+"/"+fileName;

                    Photos pot= new Photos();
                    Optional<Photos> photo=photoRepository.findById(PhotoId);
                    photo.get().setUrlS(path);
                    photoRepository.save(pot);;

                } catch (IOException ex) {
                    throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
                    // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
                }

                System.out.println("Invoking an asynchronous method. "
                        + Thread.currentThread().getName());
                // file compression in asyncronass manner
                asyncronasTaskHandling.imageCompression300X300(storeDirectory,fileName);
            }
        }

        return fileName;


    }
}
