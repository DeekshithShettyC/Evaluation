package com.robosoft.PhotosAndVideosApp.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "ac_user_profile")
@Inheritance(
        strategy = InheritanceType.JOINED
)
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id", unique=true, nullable=false)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "contact_email")
    protected String contactEmail;

    @Column(name = "contact_number")
    protected String contactNumber;


    @Column(name="profile_complete")
    protected Boolean isProfileComplete = false;

    @Column(name="location_confirmed")
    protected Boolean isLocationConfirmed = false;

    @Column(name = "disabled")
    protected Boolean disabled = false;

    @Column(name = "profile_photo")
    protected String url;

}