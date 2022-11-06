package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;

import java.util.List;

public interface IProspectiveBuyerPremiseService {
    List<ProspectiveBuyer> list(Long idPremise);
    void create(ProspectiveBuyer prospectiveBuyer, Premise premise);
}
