package com.example.foodplanner.service.impl;


import com.example.foodplanner.model.entity.UnitType;
import com.example.foodplanner.model.enumeration.UnitTypeEnum;
import com.example.foodplanner.repository.UnitTypeRepository;
import com.example.foodplanner.service.UnitTypeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UnitTypeServiceImpl implements UnitTypeService {
    private final UnitTypeRepository unitTypeRepository;

    public UnitTypeServiceImpl(UnitTypeRepository unitTypeRepository) {
        this.unitTypeRepository = unitTypeRepository;
    }


    @Override
    public void populateUnitTypes() {
        if (unitTypeRepository.count() == 0) {
            Arrays.stream(UnitTypeEnum.values()).forEach(ut->{
                UnitType unitType = new UnitType();
                unitType.setName(ut);
                unitTypeRepository.save(unitType);
            });
        }
    }
}
