package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPremise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "premises")
@Getter @Setter
public class Premise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float size;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Boolean soldOut;

    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "premise")
    private List<Image> images;

    @OneToMany(mappedBy = "premise")
    private List<Sold> solds;

    @OneToMany(mappedBy = "premise")
    private List<ProspectiveBuyerPremise> prospectiveBuyerPremises;
}
