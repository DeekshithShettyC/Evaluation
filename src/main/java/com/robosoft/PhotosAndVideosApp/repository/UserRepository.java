package com.robosoft.PhotosAndVideosApp.repository;


import com.robosoft.PhotosAndVideosApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByMobileNumber(String mobileNumber);
    Optional<User> findByUsername(final String username);

}