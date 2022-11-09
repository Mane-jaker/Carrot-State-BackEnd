package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOwnerRepository extends JpaRepository <Owner, Long> {
}
