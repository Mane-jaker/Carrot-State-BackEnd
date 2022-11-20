package com.example.carrotstatebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RealStateCodes")
@Getter @Setter
public class RealStateCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String code;

    @OneToOne(mappedBy = "code")
    private RealState realState;



}
