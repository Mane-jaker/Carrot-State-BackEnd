package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;
import com.example.carrotstatebackend.services.interfaces.IOwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements IOwnerService {

        @Override
        public List<GetOwnerResponse> list() {
            return null;
        }

        @Override
        public GetOwnerResponse get(Long id) {
            return null;
        }

        @Override
        public void delete(Long id) {

        }

        @Override
        public GetOwnerResponse create(CreateOwnerRequest request) {
            return null;
        }

        @Override
        public GetOwnerResponse update(Long id, UpdateOwnerRequest request) {
            return null;
        }

        private GetOwnerResponse from(){
            return null;
        }
}
