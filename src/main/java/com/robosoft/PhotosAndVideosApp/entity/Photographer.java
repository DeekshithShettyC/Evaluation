package com.robosoft.PhotosAndVideosApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ac_photographer_profile")
@Getter
@Setter
public class Photographer extends Profile{
    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="fk_user_id")
    private User userAccount;

    @OneToMany(mappedBy="photographer", fetch= FetchType.LAZY)
    @JsonManagedReference
    private List<Photos> photographerPhotos = new ArrayList<Photos>();

    @OneToMany(mappedBy="photographer", fetch= FetchType.LAZY)
    @JsonManagedReference
    private List<UserFavourite> userFavourite = new ArrayList<UserFavourite>();

}