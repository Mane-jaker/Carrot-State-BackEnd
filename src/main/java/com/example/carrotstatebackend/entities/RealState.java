package com.example.carrotstatebackend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "realState")
@Getter @Setter
public class RealState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)

    private String name;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 1000)
    private String profilePicture;

    private Float commissionAgent;

    @OneToMany(mappedBy = "realState")
    private List<Agent> agent;

    @OneToOne
    @JoinColumn(name = "real_state_code_id", referencedColumnName = "id")
    private RealStateCode code;

}
