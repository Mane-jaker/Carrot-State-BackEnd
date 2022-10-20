package com.example.carrotstatebackend.entities.pivots;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "prospectiveBuyer_Plot")
@Getter
@Setter
public class ProspectiveBuyerPlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProspectiveBuyer prospectiveBuyer;

    @ManyToOne
    private Plot plot;

}
