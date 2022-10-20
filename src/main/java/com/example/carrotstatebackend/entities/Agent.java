package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "angents")
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

}
