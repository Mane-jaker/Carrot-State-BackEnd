package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerPremise;
import com.example.carrotstatebackend.repositories.IProspectiveBuyerPremiseRepository;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProspectiveBuyerPremiseServiceImpl implements IProspectiveBuyerPremiseService {

    @Autowired
    private IProspectiveBuyerPremiseRepository repository;

    @Autowired
    private IPremiseService premiseService;

    @Override
    public List<ProspectiveBuyer> list(Long idPremise) {
        Premise premise = premiseService.getPremise(idPremise);
        return repository.findAllByPremise(premise)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ProspectiveBuyer prospectiveBuyer, Premise premise) {
        repository.save(from(prospectiveBuyer, premise));
    }

    @Override
    public void delete(ProspectiveBuyer prospectiveBuyer) {
        repository.deleteAllByProspectiveBuyer(prospectiveBuyer);
    }

    private ProspectiveBuyer from(ProspectiveBuyerPremise prospectiveBuyerPremise){
        return prospectiveBuyerPremise.getProspectiveBuyer();
    }

    private ProspectiveBuyerPremise from(ProspectiveBuyer prospectiveBuyer, Premise premise){
        ProspectiveBuyerPremise prospectiveBuyerPremise = new ProspectiveBuyerPremise();
        prospectiveBuyerPremise.setProspectiveBuyer(prospectiveBuyer);
        prospectiveBuyerPremise.setPremise(premise);
        return prospectiveBuyerPremise;
    }
}
