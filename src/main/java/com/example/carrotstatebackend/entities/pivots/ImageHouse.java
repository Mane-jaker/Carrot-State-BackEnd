package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.House;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "imagesHouse")
@Getter @Setter
public class ImageHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String url;

    @ManyToOne
    private House house;
}
