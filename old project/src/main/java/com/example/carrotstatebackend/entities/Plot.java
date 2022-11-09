package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPlot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "plots")
@Getter @Setter
@Entity
public class Plot {
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

    @OneToMany(mappedBy = "plot")
    private List<Image> images;

    @OneToMany(mappedBy = "plot")
    private List<Sold> solds;

    @OneToMany(mappedBy = "plot")
    private List<ProspectiveBuyerPlot> prospectiveBuyerPlots;
}
