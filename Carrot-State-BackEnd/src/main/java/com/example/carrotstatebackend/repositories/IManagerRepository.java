package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByCodeCode(Long code);
    Optional<Manager> findByMail(String email);
}
