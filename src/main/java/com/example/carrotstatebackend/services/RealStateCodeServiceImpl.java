package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.entities.RealStateCode;
import com.example.carrotstatebackend.repositories.persons.IRealStateCodeRepository;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateCodeService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RealStateCodeServiceImpl implements IRealStateCodeService {

    @Autowired
    private IRealStateCodeRepository repository;

    private final Random random = new Random();

    @Override
    public RealStateCode GenerateManagerCode() {
        Set<String> existingCodes = getAllCodesInString();
        return search(existingCodes);
    }

    private RealStateCode search(Set<String> ExistingCodes){
        String rnd = generateCode();
        if (ExistingCodes.contains(rnd)){
            return search(ExistingCodes);
        }
        return repository.save(from(rnd));
    }

    private String generateCode(){
        List<String> codeInt = Arrays.asList("", "", "");
        codeInt = codeInt.stream()
                .map(s -> s = generateChar().toString())
                .collect(Collectors.toList());

        List<String> codeChar = Arrays.asList("", "", "");
        codeChar = codeChar.stream()
                .map(s -> s = generateInt().toString())
                .collect(Collectors.toList());

        return String.join("", sort(ListUtils.union(codeInt, codeChar)));
    }

    private List<String> sort(List<String> code){
        Random r = new Random();
        for (int i = 0; i < code.size(); i++) {
            int j = r.nextInt(i + 1);
            String temp = code.get(i);
            code.set(i, code.get(j));
            code.set(j, temp);
        }
        return code;
    }

    private Integer generateInt(){
        return this.random.nextInt(10);
    }
    private Character generateChar(){
        return (char) (this.random.nextInt(26) + 'a');
    }

    private RealStateCode from(String code){
       RealStateCode realStateCode = new RealStateCode();
       realStateCode.setCode(code);
       return realStateCode;
    }

    private Set<String> getAllCodesInString(){
        return repository
                .findAll()
                .stream()
                .map(this::from).collect(Collectors.toSet());
    }

    private String from(RealStateCode code){
        return code.getCode();
    }
}
