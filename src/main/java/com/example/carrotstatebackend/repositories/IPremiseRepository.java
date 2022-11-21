package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.enums.CityState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPremiseRepository extends JpaRepository<Premise, Long> {
    List<Premise> findAllByAgent(Agent agent);
    List<Premise> findAllByCityState(CityState cityState);
    List<Premise> findAllByPriceIsLessThanEqual(Float price);
    List<Premise> findAllByPriceIsLessThanEqualAndCityState(Float price, CityState cityState);
}
