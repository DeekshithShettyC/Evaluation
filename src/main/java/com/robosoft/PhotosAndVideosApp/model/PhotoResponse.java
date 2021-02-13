package com.robosoft.PhotosAndVideosApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {

    private String userName;
    private Long userId;
    private String userPhotoUrl;
    private String photoOrVideoName;
    private String photoOrVideoDesc;
    private Long photoOrImageId;
    private String imgS;
    private String imgM;
    private String imgL;

}
