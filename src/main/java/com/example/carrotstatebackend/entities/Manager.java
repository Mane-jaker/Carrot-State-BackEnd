package com.example.carrotstatebackend.entities;


import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "managers")
@Getter @Setter
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mail;

    private String profile_photo;

    @Column(nullable = false)
    private String manager_code;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Float commission_agent;

    @OneToMany(mappedBy = "manager")
    private List<Agent> agent;
}
