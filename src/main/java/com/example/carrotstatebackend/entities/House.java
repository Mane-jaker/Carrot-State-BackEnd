package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.entities.pivots.ClientHouse;
import com.example.carrotstatebackend.entities.pivots.ImageHouse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "houses")
@Entity
@Getter @Setter
public class House{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private CityState cityState;

    private String description;

    private Integer bathRoomNum;

    @Column(nullable = false)
    private Integer rooms;

    private Integer floors;

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

    @OneToMany(mappedBy = "house")
    private List<ImageHouse> imageHouses;

    @OneToMany(mappedBy = "house")
    private List<ClientHouse> ClientHouses;

    @OneToOne(mappedBy = "house")
    private Sold sold;

}
