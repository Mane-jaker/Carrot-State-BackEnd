package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.pivots.ImagePremise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagePremiseRepository extends JpaRepository<ImagePremise, Long> {
}
