package com.example.carrotstatebackend.repositories.properties;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.enums.CityState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlotRepository extends JpaRepository<Plot, Long> {
    List<Plot> findAllByAgent(Agent agent);
    List<Plot> findAllByCityState(CityState cityState);
    List<Plot> findAllByPriceIsLessThanEqual(Float price);
    List<Plot> findAllByPriceIsLessThanEqualAndCityState(Float price, CityState cityState);
}
