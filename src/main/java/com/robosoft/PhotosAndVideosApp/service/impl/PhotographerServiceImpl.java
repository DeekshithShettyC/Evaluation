package com.robosoft.PhotosAndVideosApp.service.impl;

import com.robosoft.PhotosAndVideosApp.entity.Photographer;
import com.robosoft.PhotosAndVideosApp.entity.User;
import com.robosoft.PhotosAndVideosApp.exception.ProfileNotFoundException;
import com.robosoft.PhotosAndVideosApp.model.UserProfileInfo;
import com.robosoft.PhotosAndVideosApp.repository.PhotographerRepository;
import com.robosoft.PhotosAndVideosApp.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PhotographerServiceImpl extends AbstractProfileService implements PhotographerService {
    @Autowired
    PhotographerRepository photographerRepository;

    @Override
    public void createPhotographerProfile(String username) {
            super.createUserProfile(username);

    }

    @Override
    public void updatePhotographerProfile(Long photographerId, UserProfileInfo userProfileInfo) {
        super.updateUserProfile(photographerId,userProfileInfo);
    }

    @Override
    public UserProfileInfo getPhotographerProfile(String username) {
            return super.getUserProfile(username);
    }

    @Override
    public Photographer getPhotographerById(Long photographerId) {
        Optional<Photographer> photographerOption = photographerRepository.findById(photographerId);
        return this.getPhotographerProfile(photographerOption);
    }


    @Override
    Photographer getByUsername(String username) {
        Optional<Photographer> photographerOptional= photographerRepository.findByUsername(username);
        return this.getPhotographerUserProfile(photographerOptional);
    }
    private Photographer getPhotographerUserProfile(Optional<Photographer> photographerOptional) {
        if(!photographerOptional.isPresent()){
            throw new ProfileNotFoundException();
        }
        return photographerOptional.get();
    }
    @Override
    Photographer findById(Long id) {
        Optional<Photographer> photographerOption = photographerRepository.findById(id);
        return this.getPhotographerProfile(photographerOption);
    }

    private Photographer getPhotographerProfile(Optional<Photographer> photographerOption) {
        if(!photographerOption.isPresent()) {
            throw new ProfileNotFoundException("Requested onlineUserOption profile not found");
        }
        return photographerOption.get();
    }
    @Override
    void createProfile(User user) {
        Photographer photographer = new Photographer();;
        photographer.setContactNumber(user.getMobileNumber());
        photographer.setContactEmail(user.getEmail());
        photographer.setUserAccount(user);
        photographerRepository.save(photographer);

    }

    @Override
    void addUserRoles(User user) {

    }
}
