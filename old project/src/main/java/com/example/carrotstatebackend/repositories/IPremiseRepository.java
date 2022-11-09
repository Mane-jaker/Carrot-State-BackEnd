package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Premise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPremiseRepository extends JpaRepository<Premise, Long> {
    List<Premise> findAllByAgent(Agent agent);
}
