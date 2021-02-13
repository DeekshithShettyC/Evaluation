package com.robosoft.PhotosAndVideosApp.controller;

import com.robosoft.PhotosAndVideosApp.entity.Photos;
import com.robosoft.PhotosAndVideosApp.entity.User;
import com.robosoft.PhotosAndVideosApp.model.PhotoDetails;
import com.robosoft.PhotosAndVideosApp.model.PhotoResponse;
import com.robosoft.PhotosAndVideosApp.model.RegistrationResponse;
import com.robosoft.PhotosAndVideosApp.model.SelfRegistrationForm;
import com.robosoft.PhotosAndVideosApp.model.UserProfileInfo;
import com.robosoft.PhotosAndVideosApp.model.FavoriteItem;
import com.robosoft.PhotosAndVideosApp.service.PhotoService;
import com.robosoft.PhotosAndVideosApp.service.PhotographerService;
import com.robosoft.PhotosAndVideosApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
public class PhotographerController {
    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody final SelfRegistrationForm registrationForm) {

        User user = userService.register(registrationForm);

        RegistrationResponse regResponse = new RegistrationResponse();
        regResponse.setUserName(user.getUsername());
        regResponse.setMobileNumber(user.getMobileNumber());
        return ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(regResponse);
    }

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/{username}/profile/Photographer/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Photographer profile created successfully")
    public void createPhotographerProfile(@PathVariable final String username) {
        LOGGER.debug("Photographer profile for user with username {}", username);
        photographerService.createPhotographerProfile(username);
    }

    @RequestMapping(value = "/{photographerId}/profile/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "PhotographerId profile updated successfully")
    public void updateProfile(@PathVariable final Long photographerId, @Valid @RequestBody final UserProfileInfo UserProfileInfo) {
        LOGGER.debug("Updating profile for photographer : {} with {}", photographerId, UserProfileInfo);

        photographerService.updatePhotographerProfile(photographerId, UserProfileInfo);
    }

    @RequestMapping(value = "/{username}/profile/get", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserProfileInfo> getProfile(@PathVariable final String username) {
        LOGGER.debug("Getting photographer profile for user with username {}", username);

        UserProfileInfo photographer = photographerService.getPhotographerProfile(username);
        ResponseEntity<UserProfileInfo> responseEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(photographer);

        return responseEntity;
    }

    @RequestMapping(value = "/{photographerId}/photo/add", method = RequestMethod.POST)
   // @ResponseStatus(value = HttpStatus.OK, reason = "Photographer photo updated successfully")
    public  ResponseEntity<Photos> uploadPhotos(@PathVariable final Long photographerId, @Valid @RequestBody PhotoDetails photoDetails) {
        LOGGER.debug("Updating photo information for photographer : {} with {} ", photographerId, photoDetails);

       Photos photos= photoService.uploadPhoto(photographerId, photoDetails);

        LOGGER.debug("Updating crop information for farmer : {} with {} ", photographerId, photoDetails);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(photos);

    }

    @RequestMapping(value = "/photosOrVideo/{mediaType}/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PhotoResponse>> getAllPhotos(@PathVariable final com.robosoft.PhotosAndVideosApp.enums.MediaType mediaType) {
        List<PhotoResponse> photoResponses=  photoService.getAllPhotosOrVideos(mediaType);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(photoResponses);
    }

    @RequestMapping(value = "/{photographerId}/photos/{photoId}/{mediaType}/get", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PhotoResponse> getPhotos(@PathVariable final Long photographerId,@PathVariable final Long photoId,
                                                   @PathVariable final com.robosoft.PhotosAndVideosApp.enums.MediaType mediaType) {
        PhotoResponse photoResponse=  photoService.getPhotoOrVideoByIds(photographerId,photoId,mediaType);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(photoResponse);
    }

    @RequestMapping(value = "/{photographerId}/favourite/photos/add/{PhotosId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Favourite added successfully")
    public void addFavourite(@PathVariable final Long photographerId, @PathVariable final Long PhotosId) {
        LOGGER.debug("Updating fav information for farmer : {} with {} ", photographerId, PhotosId);
        photoService.addFavourite(photographerId, PhotosId);
    }

    @RequestMapping(value = "/{photographerId}/favourite/photos", method = RequestMethod.GET)
    public ResponseEntity<List<FavoriteItem>> getFavouriteSkus(@PathVariable final Long photographerId) {

        LOGGER.debug("Getting saved items for user : {}", photographerId);
        List<FavoriteItem> favouriteSkus =  photoService.getFavouriteSkus(photographerId);

        ResponseEntity<List<FavoriteItem>> responseEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(favouriteSkus);
        return responseEntity;
    }

}
