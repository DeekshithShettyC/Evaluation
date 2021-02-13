package com.robosoft.PhotosAndVideosApp.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegistrationResponse {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("mobile_number")
    private String mobileNumber;
}
