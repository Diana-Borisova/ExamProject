package com.example.foodplanner.service;




import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.sevice.UserServiceModel;
import com.example.foodplanner.view.UserRoleViewModel;

import java.io.IOException;
import java.util.List;

public interface UserService{
	
	User findByUsername(String username);
	

	void populateInitialUsers();

	Long registerUser(UserServiceModel userServiceModel) throws IOException;

	boolean usernameExists(String email);

	User getUserByEmail(String username);


	void updateUser(UserServiceModel userServiceModel) throws IOException;

	User getUserById(Long id);

	List<UserRoleViewModel> getAllUsers();

	void setUserRoles(Long userId, List<RoleEnum> roles);

	User getCurrentUser();
	Long getCurrentUserId();

	boolean hasCurrentUserRole(String role);


	Role getUserRoleByName(RoleEnum  roleEnum);

	List<Recipe> getFavoriteRecipesForUser(Long userId);

	List<User> findAll();

	String getProfilePicture(String username);
}
