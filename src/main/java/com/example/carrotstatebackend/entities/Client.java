package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.pivots.ClientPremise;
import com.example.carrotstatebackend.entities.pivots.ClientHouse;
import com.example.carrotstatebackend.entities.pivots.ClientPlot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Clients")
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contact;

    private Float budget;

    @OneToMany(mappedBy = "client")
    private List<Sold> sold;

    @OneToMany(mappedBy = "client")
    private List<House> houses;

    @OneToMany(mappedBy = "client")
    private List<Plot> plots;

    @OneToMany(mappedBy = "client")
    private List<Premise> premises;

    @OneToMany(mappedBy = "client")
    private List<ClientPremise> clientPremises;

    @OneToMany(mappedBy = "client")
    private List<ClientHouse> clientHouses;

    @OneToMany(mappedBy = "client")
    private List<ClientPlot> clientPlots;

}
