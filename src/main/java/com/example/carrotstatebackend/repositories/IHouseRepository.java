package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
}
