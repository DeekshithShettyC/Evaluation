package com.robosoft.PhotosAndVideosApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robosoft.PhotosAndVideosApp.enums.Category;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import lombok.Data;

@Data
public class PhotoDetails {
    @JsonProperty("photo_orVideo_name")
    private String name;

    @JsonProperty("photo_orVideo_desc")
    private String desc;

    @JsonProperty("photo_orVideo_category_name")
    private Category categoryName;

    @JsonProperty("photo_orVideo_media")
    private MediaType mediaType;

}
