package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.House;

import java.util.List;

public interface IClientHouseService {
    List<Client> list(Long idHouse);
    void create(Client client, House house);
    void delete(Client client);
}


