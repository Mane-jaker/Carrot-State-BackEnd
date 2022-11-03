package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlotRepository extends JpaRepository<Plot, Long> {
}
