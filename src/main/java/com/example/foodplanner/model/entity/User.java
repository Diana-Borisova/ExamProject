package com.example.foodplanner.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	private String phoneNumber;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String planStyle;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public User() {
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public User setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}


	public String getPlanStyle() {
		return planStyle;
	}

	public User setPlanStyle(String planStyle) {
		this.planStyle = planStyle;
		return this;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public User setRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public void addRole(Role userRole) {
		if (this.roles == null) {
			this.roles = List.of(userRole);
		} else {
			this.roles.add(userRole);
		}
	}

}
