package com.robosoft.PhotosAndVideosApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import lombok.Data;

@Data
public class FileStorageDirectory {
    @JsonProperty("mediaType_type")
    private MediaType mediaType;

    @JsonProperty("photographer_id")
    private Long photographerId;

    @JsonProperty("photo_id")
    private Long photoId;
}
