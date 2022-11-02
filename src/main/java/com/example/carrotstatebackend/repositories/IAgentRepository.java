package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, Long> {

}
