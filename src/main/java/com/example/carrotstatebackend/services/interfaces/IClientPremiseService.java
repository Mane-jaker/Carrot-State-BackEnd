package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.Client;

import java.util.List;

public interface IClientPremiseService {
    List<Client> list(Long idPremise);
    void create(Client client, Premise premise);
    void delete(Client client);
}
