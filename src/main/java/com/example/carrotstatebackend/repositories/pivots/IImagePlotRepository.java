package com.example.carrotstatebackend.repositories.pivots;

import com.example.carrotstatebackend.entities.pivots.ImagePlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagePlotRepository extends JpaRepository<ImagePlot, Long> {
}
