package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Sold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoldRepository extends JpaRepository<Sold, Long> {

}
