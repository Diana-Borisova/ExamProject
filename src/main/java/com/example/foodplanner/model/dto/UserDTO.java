package com.example.foodplanner.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
//@FieldMatch.List({
//		@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match") })
public class UserDTO {

//	@ValidEmail
	@NotNull(message = "Email is required")
	@Size(min = 1, message = "Email is required")
	private String email;

	@NotNull(message = "Username is required")
	@Size(min = 1, message = "Username is required")
	private String username;

	@NotNull(message = "Password is required")
	@Size(min = 1, message = "Password is required")
	private String password;

	@NotNull(message = "This field is required")
	@Size(min = 1, message = "This field is required")
	private String matchingPassword;

}
