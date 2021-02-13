package com.robosoft.PhotosAndVideosApp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.robosoft.PhotosAndVideosApp.enums.MediaType;
import com.robosoft.PhotosAndVideosApp.enums.Category;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ac_photo")
@Data
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_orVideo_id", unique=true, nullable=false)
    private long id;

    @Column(name = "photo_orVideo_name")
    private String name;

    @Column(name = "photo_orVideo_desc")
    private String description;

    @Column(name = "photo_orVideo_category_name")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "photo_orVideo_media")
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Column(name = "image_orVideo_small_url")
    private String urlS;

    @Column(name = "image_orVideo_medium_url")
    private String urlM;

    @Column(name = "image_orVideo_large_url")
    private String urlL;

    @ManyToOne(targetEntity = Photographer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    @JsonBackReference
    private Photographer photographer;

    @OneToMany(mappedBy="photos", fetch= FetchType.LAZY)
    @JsonManagedReference
    private List<UserFavourite> favouredByFarmers = new ArrayList<UserFavourite>();


}