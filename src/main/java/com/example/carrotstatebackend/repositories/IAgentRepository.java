package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, Long> {
    Optional<List<Agent>> findAllByManager_Id(Long manager);
}
