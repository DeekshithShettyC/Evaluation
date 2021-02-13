package com.robosoft.PhotosAndVideosApp.repository;

import com.robosoft.PhotosAndVideosApp.entity.Photos;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import com.robosoft.PhotosAndVideosApp.model.PhotoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photos,Long> {

    @Query("SELECT new com.robosoft.PhotosAndVideosApp.model.PhotoResponse(u.name,u.id,u.url,p.name,p.description," +
            "p.id,p.urlS,p.urlM,p.urlL )FROM Photos p inner join p.photographer u where p.mediaType = :mediaType")
    List<PhotoResponse> getAllPhotosOrVideos(@Param("mediaType") final MediaType mediaType);

    @Query("SELECT new com.robosoft.PhotosAndVideosApp.model.PhotoResponse(u.name,u.id,u.url,p.name,p.description," +
            "p.id,p.urlS,p.urlM,p.urlL )FROM Photos p inner join p.photographer u where p.id = :imageOrVideoId " +
            "AND u.id = :photographerId  AND p.mediaType = :mediaType")
    PhotoResponse getPhotosOrVideosById(@Param("photographerId") final Long photographerId,@Param("imageOrVideoId") final Long imageOrVideoId
    ,@Param("mediaType") final MediaType mediaType);

Optional<Photos> findById(Long photoId);
}
