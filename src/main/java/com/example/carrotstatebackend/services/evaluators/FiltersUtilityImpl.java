package com.example.carrotstatebackend.services.evaluators;

import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.services.enums.SearchOption;
import org.springframework.stereotype.Component;


@Component
public class FiltersUtilityImpl implements IFilterUtility {
    @Override
    public SearchOption filter(RequestFilters filters) {
        if (filters.getBudget() != null && filters.getCityCode() != null){
            return SearchOption.BY_ALL_FILTERS;
        }
        if (filters.getBudget() != null){
            return SearchOption.BY_PRICE;
        }
        return SearchOption.BY_CITY_CODE;
    }
}
