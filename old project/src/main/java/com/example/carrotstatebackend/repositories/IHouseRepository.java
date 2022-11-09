package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    List<House> findAllByAgent(Agent agent);
}
