package com.robosoft.PhotosAndVideosApp.service.impl;

import com.robosoft.PhotosAndVideosApp.entity.Profile;
import com.robosoft.PhotosAndVideosApp.entity.User;
import com.robosoft.PhotosAndVideosApp.exception.ProfileAlreadyExistException;
import com.robosoft.PhotosAndVideosApp.exception.ProfileNotFoundException;
import com.robosoft.PhotosAndVideosApp.model.UserProfileInfo;
import com.robosoft.PhotosAndVideosApp.repository.ProfileRepository;
import com.robosoft.PhotosAndVideosApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


public abstract class AbstractProfileService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ProfileRepository profileRepository;

    @Autowired
    protected UserService userService;



    @Transactional
    protected void createUserProfile(final String username) {
        try {
            Profile userProfile = this.getByUsername(username);
            LOGGER.debug("Profile with id {} found for {} ",userProfile.getId(), username);
            // If this reaches here that means profile exists
            throw new ProfileAlreadyExistException("Profile for the user already present ");
        } catch (ProfileNotFoundException ex) {
            // Create a profile and add roles
            LOGGER.debug("Profile not found for {} creating it",username);
            User user = userService.findByUsername(username);
            this.createProfile(user);
            this.addUserRoles(user);
        }

    }

    protected UserProfileInfo getUserProfile(final String username) {
        LOGGER.debug("Getting complete profile for user {}", username);

        Profile userProfile = this.getByUsername(username);

        UserProfileInfo userProfileInfo = new UserProfileInfo();
        userProfileInfo.setProfileId(userProfile.getId());
        userProfileInfo.setEmailId(userProfile.getContactEmail());
        userProfileInfo.setName(userProfile.getName());
        userProfileInfo.setMobileNumber(userProfile.getContactNumber());

        LOGGER.debug("Returning profile details for user {}", username);
        return userProfileInfo;
    }



    protected Profile updateUserProfile(final Long profileId, UserProfileInfo profileDto) {
        Profile userProfile = this.findById(profileId);

        LOGGER.debug("Updating profile for profile with id {}", profileId);

        userProfile.setName(profileDto.getName());
        userProfile.setContactEmail(profileDto.getEmailId());
        userProfile.setContactNumber(profileDto.getMobileNumber());

      //  if(!userProfile.getIsLocationConfirmed() && profileDto.getIsLocationConfirmed() && profileDto.getLattitude()!=null && profileDto.getLongitude()!=null) {
            LOGGER.debug("Geo location is available for profile with id {}", profileId);


        userProfile.setIsProfileComplete(true);
        LOGGER.debug("Saving profile with id {}", profileId);
        return profileRepository.save(userProfile);

    }

    // These are the methods tied to specific profile, hence making these abstract


    abstract Profile getByUsername(final String username);

    abstract Profile findById(final Long id);

    abstract void createProfile(final User user);

    abstract void addUserRoles(final User user);




}
