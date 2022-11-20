package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Premise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "imagesPremise")
@Getter
@Setter
public class ImagePremise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String url;

    @ManyToOne
    private Premise premise;
}
