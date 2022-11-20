package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.pivots.ImageHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageHouseRepository extends JpaRepository<ImageHouse, Long> {
}
