package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.enums.CityState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    List<House> findAllByAgent(Agent agent);
    List<House> findAllByPriceIsLessThanEqual(Float price);

    List<House> findAllByPriceIsLessThanEqualAndCityState(Float price, CityState cityState);
    List<House> findAllByCityState(CityState cityState);
    List<House> findAllByClient(Client owner);
}
