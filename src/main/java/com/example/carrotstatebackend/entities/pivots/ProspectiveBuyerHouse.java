package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "ProspectiveBuyer_House")
@Getter
@Setter
public class ProspectiveBuyerHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private House house;

    @ManyToOne
    private ProspectiveBuyer prospectiveBuyer;


}
