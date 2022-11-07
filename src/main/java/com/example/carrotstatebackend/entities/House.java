package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerHouse;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPlot;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;

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
    private Owner owner;

    @OneToMany(mappedBy = "house")
    private List<Image> images;

    @OneToMany(mappedBy = "house")
    private List<Sold> solds;

    @OneToMany(mappedBy = "house")
    private List<ProspectiveBuyerHouse> prospectiveBuyerHouses;

}
