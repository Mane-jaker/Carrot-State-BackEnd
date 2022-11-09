package com.example.carrotstatebackend.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ManagersCodes")
@Getter @Setter
public class ManagersCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long code;

    @OneToOne(mappedBy = "code")
    private Manager manager;



}
