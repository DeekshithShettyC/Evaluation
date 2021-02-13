package com.robosoft.PhotosAndVideosApp.service.impl;

import com.robosoft.PhotosAndVideosApp.entity.Photographer;
import com.robosoft.PhotosAndVideosApp.entity.Photos;
import com.robosoft.PhotosAndVideosApp.entity.UserFavourite;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import com.robosoft.PhotosAndVideosApp.model.FavoriteItem;
import com.robosoft.PhotosAndVideosApp.model.PhotoDetails;
import com.robosoft.PhotosAndVideosApp.model.PhotoResponse;
import com.robosoft.PhotosAndVideosApp.repository.PhotoRepository;
import com.robosoft.PhotosAndVideosApp.repository.PhotographerRepository;
import com.robosoft.PhotosAndVideosApp.repository.UserFavorateRepository;
import com.robosoft.PhotosAndVideosApp.service.PhotoService;
import com.robosoft.PhotosAndVideosApp.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {
     @Autowired
    PhotographerService photographerService;

     @Autowired
    PhotoRepository  photoRepository;

    @Autowired
    UserFavorateRepository userFavorateRepository;

    @Autowired
    PhotographerRepository  photographerRepository;


    @Override
    public Photos uploadPhoto(Long photographerId, PhotoDetails photoDetails) {
      Photographer  photographer= photographerService.getPhotographerById(photographerId);
        Photos photos=new Photos();
        photos.setDescription(photoDetails.getDesc());
        photos.setName(photoDetails.getName());
        photos.setCategory(photoDetails.getCategoryName());
        photos.setMediaType(photoDetails.getMediaType());
        photos.setPhotographer(photographer);
        return photoRepository.save(photos);
    }

    @Override
    public List<PhotoResponse> getAllPhotosOrVideos(MediaType mediaType) {
        return photoRepository.getAllPhotosOrVideos(mediaType);
    }

    @Override
    public PhotoResponse getPhotoOrVideoByIds(Long photographerId, Long photoId,MediaType mediaType) {
        return photoRepository.getPhotosOrVideosById(photographerId,photoId,mediaType);
    }

    @Override
    public void addFavourite(Long photographerId, Long PhotosId) {
        Photographer Photographer=   photographerService.getPhotographerById(photographerId);
       Optional<Photos>  photo=photoRepository.findById(PhotosId);
        UserFavourite  userFavourite=new UserFavourite();
        userFavourite.setPhotographer(Photographer);
        userFavourite.setPhotos(photo.get());
        userFavorateRepository.save(userFavourite);

    }

    @Override
    public List<FavoriteItem> getFavouriteSkus(Long photographerId) {
        return null;
    }

//    @Override
//    public List<FavoriteItem> getFavouriteSkus(Long photographerId) {
//        List<UserFavourite> PhotographerPhotoList= photographerRepository.findByPhotographerId(photographerId);
//        List<FavoriteItem> favPhotoList = new ArrayList<FavoriteItem>();
//
//        for (UserFavourite favs : PhotographerPhotoList) {
//            favPhotoList.add(this.buildSkuDisplayItem(favs.getPhotos()));
//        }
//        return favPhotoList;
//
//    }
//
//    public FavoriteItem buildSkuDisplayItem(Photos photo) {
//        FavoriteItem userFavourite = new FavoriteItem();
//        skuDisplayItem.setId(sku.getId());
//        skuDisplayItem.setMainLabel(sku.getName());
//        skuDisplayItem.setDescription(sku.getDescription());
//        return userFavourite;
//    }
}
