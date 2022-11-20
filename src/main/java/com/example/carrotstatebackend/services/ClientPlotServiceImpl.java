package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.pivots.ClientPlot;
import com.example.carrotstatebackend.repositories.IClientPlotRepository;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import com.example.carrotstatebackend.services.interfaces.IClientPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientPlotServiceImpl implements IClientPlotService {

    @Autowired
    private IClientPlotRepository repository;

    @Autowired
    private IPlotService plotService;

    @Override
    public List<Client> list(Long idPlot) {
        Plot plot = plotService.getPlot(idPlot);
        return repository.findAllByPlot(plot)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Client client, Plot plot) {
        repository.save(from(client, plot));
    }

    @Override
    public void delete(Client client) {
        repository.deleteAllByClient(client);
    }

    private Client from(ClientPlot clientPlot){
        return clientPlot.getClient();
    }

    private ClientPlot from(Client client, Plot plot){
        ClientPlot clientPlot = new ClientPlot();
        clientPlot.setClient(client);
        clientPlot.setPlot(plot);
        return clientPlot;
    }
}
