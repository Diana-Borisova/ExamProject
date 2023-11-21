package com.example.foodplanner.service;




import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.view.UserRoleViewModel;

import java.io.IOException;
import java.util.List;

public interface UserService{
	
	public User findByUsername(String username);
	

	void populateInitialUsers();

	Long registerUser(UserServiceModel userServiceModel) throws IOException;

	boolean usernameExists(String email);

	User getUserByEmail(String username);


	void updateUser(UserServiceModel userServiceModel) throws IOException;

	User getUserById(Long id);

	List<UserRoleViewModel> getAllUsers();

	void setUserRoles(Long userId, List<RoleEnum> roles);

	public User getCurrentUser();
	public Long getCurrentUserId();

	public boolean hasCurrentUserRole(String role);

}
