package com.robosoft.PhotosAndVideosApp.service;



import com.robosoft.PhotosAndVideosApp.entity.User;
import com.robosoft.PhotosAndVideosApp.model.SelfRegistrationForm;

import java.util.List;

public interface UserService {
    public User register(final SelfRegistrationForm userAccount);
    public User findByEmail(final String email);

    public User findByMobileNumber(final String mobileNumber);
    public User save(final User user);
    public List<User> findAll();
    public User findById(final Long id);
    public User updateUser(final User user);
    public User findByUsername(final String username);
}
