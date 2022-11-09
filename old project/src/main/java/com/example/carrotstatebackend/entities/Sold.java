package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Sold{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Float comission;

    @ManyToOne Owner owner;

    @ManyToOne Agent agent;

    @ManyToOne Premise premise;

    @ManyToOne Plot plot;

    @ManyToOne House house;


}
