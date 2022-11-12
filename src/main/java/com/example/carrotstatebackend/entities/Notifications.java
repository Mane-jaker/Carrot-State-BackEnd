package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
@Getter @Setter
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Manager manager;


}
