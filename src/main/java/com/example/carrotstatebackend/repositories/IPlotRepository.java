package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlotRepository extends JpaRepository<Plot, Long> {
    List<Plot> findAllByAgent(Agent agent);
}
