package com.example.foodplanner.model.entity;

import com.example.foodplanner.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{



	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum name;



}
