package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.RealState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRealStateRepository extends JpaRepository<RealState, Long> {
    Optional<RealState> findByCode_Code(String code_code);
    Optional<RealState> findByEmail(String email);
}
