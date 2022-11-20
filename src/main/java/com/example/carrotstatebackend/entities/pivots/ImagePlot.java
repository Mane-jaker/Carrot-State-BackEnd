package com.example.carrotstatebackend.entities.pivots;

import com.example.carrotstatebackend.entities.Plot;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "imagesPlot")
@Getter @Setter
public class ImagePlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String url;

    @ManyToOne
    private Plot plot;
}
