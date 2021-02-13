package com.robosoft.PhotosAndVideosApp.repository;

import com.robosoft.PhotosAndVideosApp.entity.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavorateRepository extends JpaRepository<UserFavourite,Long> {
}
