package com.robosoft.PhotosAndVideosApp.service;

import com.robosoft.PhotosAndVideosApp.entity.Photos;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import com.robosoft.PhotosAndVideosApp.model.FavoriteItem;
import com.robosoft.PhotosAndVideosApp.model.PhotoDetails;
import com.robosoft.PhotosAndVideosApp.model.PhotoResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PhotoService {
    public Photos uploadPhoto(final Long photographerId, PhotoDetails photoDetails);
    public List<PhotoResponse> getAllPhotosOrVideos(MediaType mediaType);
    public PhotoResponse getPhotoOrVideoByIds(Long photographerId, Long photoId,MediaType mediaType);
    public void addFavourite(Long photographerId, Long PhotosId);
    public List<FavoriteItem>  getFavouriteSkus(Long photographerId);
}
