package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.pivots.ClientPremise;
import com.example.carrotstatebackend.repositories.IClientPremiseRepository;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import com.example.carrotstatebackend.services.interfaces.IClientPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientPremiseServiceImpl implements IClientPremiseService {

    @Autowired
    private IClientPremiseRepository repository;

    @Autowired
    private IPremiseService premiseService;

    @Override
    public List<Client> list(Long idPremise) {
        Premise premise = premiseService.getPremise(idPremise);
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
