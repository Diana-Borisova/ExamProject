package com.example.foodplanner.repository;



import com.example.foodplanner.model.entity.UnitType;
import com.example.foodplanner.model.enumeration.UnitTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Long> {
    Optional<UnitType> getUnitTypeByName(UnitTypeEnum name);
    UnitType findByName(UnitTypeEnum name);

}
