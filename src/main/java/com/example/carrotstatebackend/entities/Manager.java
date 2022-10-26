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

    @Column(length = 1000)
    private String profilePicture;

    @Column(nullable = false)
    private String managerCode;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Float commissionAgent;

    @OneToMany(mappedBy = "manager")
    private List<Agent> agent;

    @ManyToOne
    private Notifications notifications;
}
