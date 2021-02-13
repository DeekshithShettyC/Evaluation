package com.robosoft.PhotosAndVideosApp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserProfileInfo {

    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("name")
    @NotNull(message = "{name can't be null}")
    @NotBlank(message = "{name can't be blank}")
    private String name;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("email_id")
    private String emailId;

}
