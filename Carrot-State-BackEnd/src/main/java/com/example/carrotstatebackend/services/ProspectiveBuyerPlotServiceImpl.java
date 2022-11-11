package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPlot;
import com.example.carrotstatebackend.repositories.IProspectiveBuyerPlotRepository;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProspectiveBuyerPlotServiceImpl implements IProspectiveBuyerPlotService {

    @Autowired
    private IProspectiveBuyerPlotRepository repository;

    @Autowired
    private IPlotService plotService;

    @Override
    public List<ProspectiveBuyer> list(Long idPlot) {
        Plot plot = plotService.getPlot(idPlot);
        return repository.findAllByPlot(plot)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ProspectiveBuyer prospectiveBuyer, Plot plot) {
        repository.save(from(prospectiveBuyer, plot));
    }

    @Override
    public void delete(ProspectiveBuyer prospectiveBuyer) {
        repository.deleteAllByProspectiveBuyer(prospectiveBuyer);
    }

    private ProspectiveBuyer from(ProspectiveBuyerPlot prospectiveBuyerPlot){
        return prospectiveBuyerPlot.getProspectiveBuyer();
    }

    private ProspectiveBuyerPlot from(ProspectiveBuyer prospectiveBuyer, Plot plot){
        ProspectiveBuyerPlot prospectiveBuyerPlot = new ProspectiveBuyerPlot();
        prospectiveBuyerPlot.setProspectiveBuyer(prospectiveBuyer);
        prospectiveBuyerPlot.setPlot(plot);
        return prospectiveBuyerPlot;
    }
}
