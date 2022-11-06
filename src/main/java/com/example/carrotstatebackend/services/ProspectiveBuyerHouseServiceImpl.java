package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerHouseRequest;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.entities.pivots.ProspectiveBuyerHouse;
import com.example.carrotstatebackend.repositories.IProspectiveBuyerHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerHouseService;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProspectiveBuyerHouseServiceImpl implements IProspectiveBuyerHouseService {

    @Autowired
    private IProspectiveBuyerHouseRepository repository;

    @Autowired
    private IHouseService houseService;

    @Override
    public List<ProspectiveBuyer> list(Long idHouse) {
        House house = houseService.getHouse(idHouse);
        return repository.findAllByHouse(house)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ProspectiveBuyer prospectiveBuyer, House house) {
        repository.save(from(prospectiveBuyer, house));
    }

    @Override
    public void delete(Long id) {}

    private ProspectiveBuyer from(ProspectiveBuyerHouse prospectiveBuyerHouse){
        return prospectiveBuyerHouse.getProspectiveBuyer();
    }

    private ProspectiveBuyerHouse from(ProspectiveBuyer prospectiveBuyer, House house){
        ProspectiveBuyerHouse prospectiveBuyerHouse = new ProspectiveBuyerHouse();
        prospectiveBuyerHouse.setHouse(house);
        prospectiveBuyerHouse.setProspectiveBuyer(prospectiveBuyer);
        return prospectiveBuyerHouse;
    }
}
