package com.example.carrotstatebackend.repositories.persons;

import com.example.carrotstatebackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository <Client, Long> {
    Optional<Client> findByEmail(String email);
}
