package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProspectiveBuyerRepository extends JpaRepository <ProspectiveBuyer, Long> {
}
