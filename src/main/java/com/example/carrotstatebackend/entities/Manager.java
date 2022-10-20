package com.example.carrotstatebackend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "managers")
@Getter @Setter
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String mail;

    @Column(nullable = false)
    private String profile_photo;

    private String manager_code;

    private String password;

    @Column(nullable = false)
    private Float commission_agent;


}
