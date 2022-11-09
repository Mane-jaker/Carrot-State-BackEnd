package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProspectiveBuyerPlotRepository extends JpaRepository<ProspectiveBuyerPlot, Long> {
    List<ProspectiveBuyerPlot> findAllByPlot(Plot plot);
}
