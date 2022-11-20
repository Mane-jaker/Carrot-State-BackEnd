package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.House;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "ClientHouses")
@Getter
@Setter
public class ClientHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private House house;

    @ManyToOne
    private Client client;
}
