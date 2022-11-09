package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.ManagersCode;
import com.example.carrotstatebackend.repositories.IManagersCodeRepository;
import com.example.carrotstatebackend.services.interfaces.IManagersCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagersCodeServiceImpl implements IManagersCode {

    @Autowired
    private IManagersCodeRepository repository;

    @Override
    public ManagersCode GenerateManagerCode() {
        Set<Long> ExistingCodes = getAllCodesInString();
        Random random = new Random();
        Long possible;
        ManagersCode managersCode = new ManagersCode();
        boolean found = false;
        while (!found){
            long rnd = random.nextLong();
            if (!ExistingCodes.contains(rnd) && rnd > 0L){
                possible = rnd;
                managersCode = repository.save(from(possible));
                found = true;
            }
        }
        return managersCode;
    }

    @Override
    public Long GetManagersCode(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new).getCode();
    }

    private ManagersCode from(Long code){
       ManagersCode managersCode = new ManagersCode();
       managersCode.setCode(code);
        return managersCode;
    }

    private Set<Long> getAllCodesInString(){
        return repository
                .findAll()
                .stream()
                .map(this::from).collect(Collectors.toSet());
    }

    private Long from(ManagersCode code){
        return code.getCode();
    }
}
