package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.entities.pivots.ClientPlot;
import com.example.carrotstatebackend.entities.pivots.ImagePlot;
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

    private CityState cityState;

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
    private Client client;

    @OneToMany(mappedBy = "plot")
    private List<ImagePlot> imagesPlot;

    @OneToMany(mappedBy = "plot")
    private List<ClientPlot> ClientPlots;

    @OneToOne(mappedBy = "plot")
    private Sold sold;

}
