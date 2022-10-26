package com.example.carrotstatebackend.repositories;

import com.example.carrotstatebackend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Long> {
}
