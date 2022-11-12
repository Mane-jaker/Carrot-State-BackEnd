package com.example.carrotstatebackend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Owner")
@Getter @Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String contact;

    @ManyToOne
    private Agent agent;

    @OneToMany(mappedBy = "owner")
    private List<Premise> premises;

    @OneToMany(mappedBy = "owner")
    private List<Plot> plots;

    @OneToMany(mappedBy = "owner")
    private List<House> houses;

    @OneToMany(mappedBy = "owner")
    private List<Sold> solds;
}
