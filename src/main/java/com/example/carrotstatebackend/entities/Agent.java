package com.example.carrotstatebackend.entities;

import com.example.carrotstatebackend.entities.enums.State;
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
    private State state;

    @ManyToOne
    private RealState realState;

    @OneToMany(mappedBy = "agent")
    private List<Plot> plot;

    @OneToMany(mappedBy = "agent")
    private List<House> house;

    @OneToMany(mappedBy = "agent")
    private List<Premise> premises;

    @OneToMany(mappedBy = "agent")
    private List<Sold> sold;

}

/*

* */
