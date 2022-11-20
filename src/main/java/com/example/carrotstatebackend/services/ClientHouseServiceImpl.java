package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.pivots.ClientHouse;
import com.example.carrotstatebackend.repositories.IClientHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import com.example.carrotstatebackend.services.interfaces.IClientHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientHouseServiceImpl implements IClientHouseService {

    @Autowired
    private IClientHouseRepository repository;

    @Autowired
    private IHouseService houseService;

    @Override
    public List<Client> list(Long idHouse) {
        House house = houseService.getHouse(idHouse);
        return repository.findAllByHouse(house)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Client client, House house) {
        repository.save(from(client, house));
    }

    @Override
    public void delete(Client client) {
        repository.deleteAllByClient(client);
    }

    private Client from(ClientHouse clientHouse){
        return clientHouse.getClient();
    }

    private ClientHouse from(Client client, House house){
        ClientHouse clientHouse = new ClientHouse();
        clientHouse.setHouse(house);
        clientHouse.setClient(client);
        return clientHouse;
    }
}
