package com.robosoft.PhotosAndVideosApp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelfRegistrationForm {

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("Password")
    private String password;

}