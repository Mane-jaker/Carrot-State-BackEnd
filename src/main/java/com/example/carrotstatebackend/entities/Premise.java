package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.entities.pivots.ClientPremise;
import com.example.carrotstatebackend.entities.pivots.ImagePremise;
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

    @OneToMany(mappedBy = "premise")
    private List<ImagePremise> imagePremises;

    @OneToMany(mappedBy = "premise")
    private List<ClientPremise> clientPremises;

    @OneToOne(mappedBy = "premise")
    private Sold sold;
}
