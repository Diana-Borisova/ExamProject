package com.example.foodplanner.model.entity;


import com.example.foodplanner.model.enumeration.UnitTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "unit_types")
public class UnitType extends BaseEntity{



	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UnitTypeEnum name;



}
