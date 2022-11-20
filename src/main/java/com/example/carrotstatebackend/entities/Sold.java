package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
@Getter @Setter
public class Sold{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Float comission;

    @ManyToOne private Agent agent;

    @ManyToOne private Client client;

    @OneToOne
    @JoinColumn(name = "premise_id", referencedColumnName = "id")
    private Premise premise;

    @OneToOne
    @JoinColumn(name = "plot_id", referencedColumnName = "id")
    private Plot plot;

    @OneToOne
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;


}
