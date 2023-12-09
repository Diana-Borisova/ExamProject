package com.example.foodplanner.model.entity;



import com.example.foodplanner.model.enumeration.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	public Role() {
	}

	public RoleEnum getName() {
		return name;
	}

	public Role setName(RoleEnum name) {
		this.name = name;
		return this;
	}
}
