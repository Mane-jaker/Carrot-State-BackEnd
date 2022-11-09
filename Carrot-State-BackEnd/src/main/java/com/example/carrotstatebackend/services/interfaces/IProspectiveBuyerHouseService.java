package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;

import java.util.List;

public interface IProspectiveBuyerHouseService {
    List<ProspectiveBuyer> list(Long idHouse);
    void create(ProspectiveBuyer prospectiveBuyer, House house);
    void delete(Long id);
}


