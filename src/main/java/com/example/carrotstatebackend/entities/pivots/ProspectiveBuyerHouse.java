package com.example.carrotstatebackend.entities.pivots;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProspectiveBuyer_House")
@Getter
@Setter
public class ProspectiveBuyerHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProspectiveBuyer prospectiveBuyer;

    @ManyToOne
    private House house;
}
