package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.pivots.ClientPremise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientPremiseRepository extends JpaRepository<ClientPremise, Long> {
    List<ClientPremise> findAllByPremise(Premise premise);
    void deleteAllByClient(Client client);
}
