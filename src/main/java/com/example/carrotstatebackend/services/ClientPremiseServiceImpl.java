package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.pivots.ClientPremise;
import com.example.carrotstatebackend.repositories.pivots.IClientPremiseRepository;
import com.example.carrotstatebackend.services.interfaces.pivtos.IBaseClientPropertyService;
import com.example.carrotstatebackend.services.interfaces.properties.IBasePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientPremiseServiceImpl implements IBaseClientPropertyService<Premise> {

    @Autowired
    private IClientPremiseRepository repository;

    @Autowired
    private IBasePropertyService<Premise> premiseService;

    @Override
    public List<Client> list(Long idPremise) {
        Premise premise = premiseService.getPropertyE(idPremise);
        return repository.findAllByPremise(premise)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Client client, Premise premise) {
        repository.save(from(client, premise));
    }

    @Override
    public void delete(Client client) {
        repository.deleteAllByClient(client);
    }

    private Client from(ClientPremise prospectiveBuyerPremise){
        return prospectiveBuyerPremise.getClient();
    }

    private ClientPremise from(Client client, Premise premise){
        ClientPremise prospectiveBuyerPremise = new ClientPremise();
        prospectiveBuyerPremise.setClient(client);
        prospectiveBuyerPremise.setPremise(premise);
        return prospectiveBuyerPremise;
    }
}
