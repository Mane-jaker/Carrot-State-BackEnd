package com.example.carrotstatebackend.services.interfaces.pivtos;

import com.example.carrotstatebackend.entities.Client;

import java.util.List;

public interface IBaseClientPropertyService<T>{
    List<Client> list(Long idClient);
    void create(Client client, T property);
    void delete(Client client);
}
