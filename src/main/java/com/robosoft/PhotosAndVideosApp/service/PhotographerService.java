package com.robosoft.PhotosAndVideosApp.service;

import com.robosoft.PhotosAndVideosApp.entity.Photographer;
import com.robosoft.PhotosAndVideosApp.model.UserProfileInfo;

public interface PhotographerService {
    public void createPhotographerProfile(final String username);
    public void  updatePhotographerProfile(final Long photographerId, UserProfileInfo userProfileInfo);
    public UserProfileInfo getPhotographerProfile(String username);
    public Photographer getPhotographerById(final Long photographerId);
}
