package com.robosoft.PhotosAndVideosApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ac_user_fav")
@Data
public class UserFavourite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "farmer_fav_sku_id", unique=true, nullable=false)
    private Long id;

    @ManyToOne(targetEntity = Photographer.class, optional=false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_profile_id")
    @JsonBackReference
    private Photographer photographer;

    @ManyToOne(targetEntity = Photos.class)
    @JoinColumn(name = "fk_photo_id")
    @JsonBackReference
    private Photos photos;
}