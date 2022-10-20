package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Table(name = "angents")
@Getter @Setter
public class ProspectiveBuyer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contacto;

    private Float presupuesto;



}
