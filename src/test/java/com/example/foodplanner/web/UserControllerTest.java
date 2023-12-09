package com.example.foodplanner.web;


import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.exception.EntityNotFoundException;
import com.example.foodplanner.repository.CommentRepository;
import com.example.foodplanner.repository.LogRepository;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    private User user1;

    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1
                .setEmail("ivan@abv.bg")
                .setFirstName("ivan")
                .setLastName("ivanov")
                .setPassword("testpass")
                .setProfilePicture("http://res.cloudinary.com/dmd3mttpc/image/upload/v1702077064/lzovxowavdupbno2rxze.png")
                .setPhoneNumber("0888888888");
        userRepository.save(user1);


    }
    @AfterEach
    public void cleanUp() {
        logRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testRegisterUserGet() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register-user")).
                andExpect(model().attributeExists("usernameOccupied"));
    }

    @Test
    public void testLoginGet() throws Exception {
        mockMvc.perform(get("/users/login")).
                andExpect(status().isOk()).
                andExpect(view().name("login"));

    }

    @Test
    @WithMockUser(username = "ivan@abv.bg")
    public void testUserProfileGet() throws Exception {
        mockMvc.perform(get("/users/" + userRepository.findByEmail("ivan@abv.bg").
                orElseThrow(() -> new EntityNotFoundException("User")).getId())).
                andExpect(status().isOk()).
                andExpect(view().name("user-profile")).
                andExpect(model().attributeExists("user", "isOwner"));
    }

    @Test
    @WithMockUser(username = "ivan@abv.bg")
    public void testMyProfileGet() throws Exception {
        mockMvc.perform(get("/users/my-profile")).
                andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "ivan@abv.bg")
    public void testEditProfileGet() throws Exception {
        mockMvc.perform(get("/users/edit-profile")).
                andExpect(status().isOk()).
                andExpect(view().name("edit-user")).
                andExpect(model().attributeExists("usernameOccupied", "userData"));
    }

    @WithMockUser(username = "ivan@abv.bg", roles = {"USER", "ADMIN"})
    @Test
    public void testEditProfilePatch() throws Exception {
        mockMvc.perform(patch("/users/edit-profile").
                param("firstName", "Changed").
                param("lastName", "Changed").
                param("email", "changed@mail.com").
                param("phoneNumber", "0888888888")
                .with(csrf())).
                andExpect(status().is3xxRedirection());

        assertEquals("changed@mail.com", userRepository.findById(user1.getId())
                .orElseThrow(() -> new EntityNotFoundException("User")).getEmail());
        assertEquals("Changed", userRepository.findById(user1.getId())
                .orElseThrow(() -> new EntityNotFoundException("User")).getFirstName());
        assertEquals("Changed", userRepository.findById(user1.getId())
                .orElseThrow(() -> new EntityNotFoundException("User")).getLastName());
        assertEquals("0888888888", userRepository.findById(user1.getId())
                .orElseThrow(() -> new EntityNotFoundException("User")).getPhoneNumber());
    }

    @WithMockUser(username = "ivan@abv.bg", roles = {"USER", "ADMIN"})
    @Test
    public void testEditProfilePatchBindError() throws Exception {
        mockMvc.perform(patch("/users/edit-profile").
                param("firstName", "C").
                param("lastName", "Changed").
                param("email", "changed@mail.com").
                param("phoneNumber", "0888888888")
                .with(csrf())).
                andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userEditBindingModel"))
                .andExpect(flash().attributeExists("userEditBindingModel"));
    }

    @WithMockUser(username = "ivan@abv.bg", roles = {"USER", "ADMIN"})
    @Test
    public void testEditProfilePatchExistingEmail() throws Exception {
        userRepository.save(new User().
                setEmail("peter@abv.bg").
                setFirstName("Peter").
                setLastName("Petrov").
                setPhoneNumber("09090909").
                setProfilePicture("http://fakepic").
                setPassword("mockpass"));
        mockMvc.perform(patch("/users/edit-profile").
                param("firstName", "Changed").
                param("lastName", "Changed").
                param("email", "peter@abv.bg").
                param("phoneNumber", "0888888888")
                .with(csrf())).
                andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("usernameOccupied"))
                .andExpect(flash().attributeExists("userEditBindingModel"));
    }


    @Test
    public void testRegisterUserPost() throws Exception {
        mockMvc.perform(post("/users/register").
                param("firstName", "Register").
                param("lastName", "Test").
                param("email", "peter@abv.bg").
                param("birthDate", "2000-03-03").
                param("phoneNumber", "0808080808").
                param("password", "mockpass").
                param("confirmPassword", "mockpass").
                with(csrf())).
                andExpect(status().is3xxRedirection());

        assertEquals(2, userRepository.count());
    }
    @Test
    public void testRegisterUserPostBindError() throws Exception {
        mockMvc.perform(post("/users/register").
                param("firstName", "R").
                param("lastName", "Test").
                param("email", "peter@abv.bg").
                param("birthDate", "2000-03-03").
                param("phoneNumber", "0808080808").
                param("password", "mockpass").
                param("confirmPassword", "mockpass").
                with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(flash().attributeExists("userRegisterBindingModel")).
                andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userRegisterBindingModel"));

        assertEquals(1, userRepository.count());
    }
    @Test
    public void testRegisterUserPostEmailExists() throws Exception {
        mockMvc.perform(post("/users/register").
                param("firstName", "Register").
                param("lastName", "Test").
                param("email", "ivan@abv.bg").
                param("birthDate", "2000-03-03").
                param("phoneNumber", "0808080808").
                param("password", "mockpass").
                param("confirmPassword", "mockpass").
                with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(flash().attributeExists("userRegisterBindingModel")).
                andExpect(flash().attributeExists("usernameOccupied"));

        assertEquals(1, userRepository.count());
    }
}
