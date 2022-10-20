package com.example.carrotstatebackend.entities;

import java.util.List;

import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerHouse;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPlot;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPremise;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "prospectiveBuyer")
@Getter @Setter
public class ProspectiveBuyer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contact;

    private Float budget;

    @OneToMany(mappedBy = "prospectiveBuyer")
    private List<ProspectiveBuyerPremise> prospectiveBuyerPremises;

    @OneToMany(mappedBy = "prospectiveBuyer")
    private List<ProspectiveBuyerHouse> prospectiveBuyerHouses;

    @OneToMany(mappedBy = "prospectiveBuyer")
    private List<ProspectiveBuyerPlot> prospectiveBuyerPlots;

}
