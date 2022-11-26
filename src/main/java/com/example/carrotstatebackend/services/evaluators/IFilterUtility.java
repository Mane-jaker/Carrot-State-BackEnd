package com.example.carrotstatebackend.services.evaluators;

import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.services.enums.SearchOption;


public interface IFilterUtility {
    SearchOption filter(RequestFilters filter);
}
