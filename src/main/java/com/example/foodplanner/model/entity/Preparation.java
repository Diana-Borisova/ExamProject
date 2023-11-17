package com.example.foodplanner.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preparations")
public class Preparation extends BaseEntity{



	@Column(name = "description")
	private String description;

	public Preparation(Preparation preparation) {
		this.description = preparation.description;
	}

}
