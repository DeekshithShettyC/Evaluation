package com.robosoft.PhotosAndVideosApp.service.impl;


import com.robosoft.PhotosAndVideosApp.entity.Role;
import com.robosoft.PhotosAndVideosApp.entity.User;
import com.robosoft.PhotosAndVideosApp.exception.MobileAlreadyRegisteredException;
import com.robosoft.PhotosAndVideosApp.model.SelfRegistrationForm;
import com.robosoft.PhotosAndVideosApp.repository.UserRepository;
import com.robosoft.PhotosAndVideosApp.service.RoleService;
import com.robosoft.PhotosAndVideosApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;



    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUsername(username);
        return user;
    }

    @Override
    @Transactional
    public User register(final SelfRegistrationForm registrationForm) {

        if (isMobileAlreadyRegistered(registrationForm.getMobileNumber())) {
            throw new MobileAlreadyRegisteredException("Mobile number already registered");
        }

        final User user = buildUser(registrationForm, true, true, false, false);

        Role role = roleService.getByRole("ROLE_USER");
        if (role != null) {
            user.getRoles().add(role);
        }


        return userRepository.save(user);
    }

    @Override
    public User findByUsername(final String username) {
        LOGGER.debug("Calling repository to get user object for user name {} ", username);
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            LOGGER.error("User {} not found ", username);
            throw new UsernameNotFoundException("User not found");
        }
        LOGGER.debug("Returning user object for user name {} ", username);
        return user.get();
    }

    private User buildUser(SelfRegistrationForm userAccount, boolean isActive, boolean isMobileVerified,
                           boolean isProfileComplete, boolean isPasswordExpired) {
        // Build the domain model
        final User user = new User();
        user.setMobileNumber(userAccount.getMobileNumber());

        // All defaults
        user.setEnabled(isActive);
        user.setMobileVerified(isMobileVerified);
        user.setCredentialsNonExpired(!isPasswordExpired);

        user.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));

        user.setEmail(userAccount.getEmailId());
        user.setUsername(userAccount.getMobileNumber());

        return user;
    }

    private boolean isMobileAlreadyRegistered(final String mobileNumber) {
        return userRepository.findByMobileNumber((mobileNumber)) != null;
    }

    // Below methods may not be needed, check this while code refractoring
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByMobileNumber(final String registeredMobileNumber) {
        return userRepository.findByMobileNumber(registeredMobileNumber);
    }

    @Override
    public User findById(final Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;

    }
}