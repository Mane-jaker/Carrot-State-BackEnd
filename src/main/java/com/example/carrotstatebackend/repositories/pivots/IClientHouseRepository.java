package com.example.carrotstatebackend.repositories.pivots;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.pivots.ClientHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientHouseRepository extends JpaRepository<ClientHouse, Long> {
    List<ClientHouse> findAllByHouse(House house);
    void deleteAllByClient(Client client);
}
