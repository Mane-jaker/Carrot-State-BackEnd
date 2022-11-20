package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ClientPremises")
@Getter @Setter
public class ClientPremise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Premise premise;

    @ManyToOne
    private Client client;
}
