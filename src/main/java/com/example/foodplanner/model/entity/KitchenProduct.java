package com.example.foodplanner.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kitchen_products")
public class KitchenProduct extends BaseEntity{



	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "food_product_id")
	private FoodProduct foodProduct;

	@Column(name = "ammount")
	private float ammount;

	@Column(name = "expiration_date")
	private Date expirationDate;

}
