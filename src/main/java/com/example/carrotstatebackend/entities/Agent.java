package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "agents")
@Entity
@Getter @Setter
public class Agent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String profilePicture;

    private Integer lastSoldProperty;

    private Long numberOfSells;

    private Long numberOfProperties;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "agent")
    private List<Owner> owner;

    @OneToMany(mappedBy = "agent")
    private List<Plot> plot;

    @OneToMany(mappedBy = "agent")
    private List<House> house;

    @OneToMany(mappedBy = "agent")
    private List<Premise> premises;

    @OneToMany(mappedBy = "agent")
    private List<Sold> sold;

}
