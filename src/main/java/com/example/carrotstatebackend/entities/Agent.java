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

    @Column(length = 1000)
    private String profilePicture;

    private Integer numberOfSales;

    private Integer numberOfProperties;

    @Column(nullable = false)
    private Boolean state;

    @ManyToOne
    private Manager manager;

    @ManyToOne
    private Notifications notifications;

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
