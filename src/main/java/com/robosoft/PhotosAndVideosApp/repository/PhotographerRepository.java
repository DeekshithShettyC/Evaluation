package com.robosoft.PhotosAndVideosApp.repository;

import com.robosoft.PhotosAndVideosApp.entity.Photographer;
import com.robosoft.PhotosAndVideosApp.entity.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
    @Query("SELECT p FROM Photographer p inner join p.userAccount u where u.username = :username")
    Optional<Photographer> findByUsername(@Param("username") final String username);


    public List<UserRepository> findByPhotographerId(final Long photographerId );
}
