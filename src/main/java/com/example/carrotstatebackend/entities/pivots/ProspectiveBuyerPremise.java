package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prospectiveBuyer_Premise")
@Getter @Setter
public class ProspectiveBuyerPremise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProspectiveBuyer prospectiveBuyer;

    @ManyToOne
    private Premise premise;
}
