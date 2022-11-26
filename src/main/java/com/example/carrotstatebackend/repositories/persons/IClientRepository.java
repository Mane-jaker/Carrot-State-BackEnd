package com.example.carrotstatebackend.repositories.persons;

import com.example.carrotstatebackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository <Client, Long> {
}
