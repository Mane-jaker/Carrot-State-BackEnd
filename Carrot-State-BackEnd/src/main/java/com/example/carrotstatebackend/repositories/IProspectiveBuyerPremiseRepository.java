package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPremise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProspectiveBuyerPremiseRepository extends JpaRepository<ProspectiveBuyerPremise, Long> {
    List<ProspectiveBuyerPremise> findAllByPremise(Premise premise);
}
