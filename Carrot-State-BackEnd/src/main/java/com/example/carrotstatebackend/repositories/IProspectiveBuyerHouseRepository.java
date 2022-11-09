package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProspectiveBuyerHouseRepository extends JpaRepository<ProspectiveBuyerHouse, Long> {
    List<ProspectiveBuyerHouse> findAllByHouse(House house);
}
