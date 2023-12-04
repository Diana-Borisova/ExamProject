package com.example.foodplanner.service.impl;

import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.repository.UserRepository;
import com.example.foodplanner.service.FoodPlannerUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class FoodPlannerUserDetailsServiceTest {

    private FoodPlannerUserDetailsService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new FoodPlannerUserDetailsService(mockUserRepository);
    }
    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exits");
                }
        );
    }

    @Test
    void testExistingUser() {
        // prepare data
        User userEntity = new User();
        userEntity.setEmail("daina@abv.bg");
        userEntity.setPassword("xyz");

        Role roleUser = new Role();
        roleUser.setName(RoleEnum.USER);
        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ADMIN);

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        // configure mocks
        Mockito.when(mockUserRepository.findByEmail("test")).
                thenReturn(Optional.of(userEntity));

        // test
        UserDetails userDetails = serviceToTest.loadUserByUsername("test");

        Assertions.assertEquals(userEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.
                getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }
}
