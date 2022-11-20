package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Plot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ClientPlots")
@Getter
@Setter
public class ClientPlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Plot plot;

    @ManyToOne
    private Client client;
}
