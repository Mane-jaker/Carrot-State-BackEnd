package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.pivots.ClientPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientPlotRepository extends JpaRepository<ClientPlot, Long> {
    List<ClientPlot> findAllByPlot(Plot plot);
    void deleteAllByClient(Client client);
}
