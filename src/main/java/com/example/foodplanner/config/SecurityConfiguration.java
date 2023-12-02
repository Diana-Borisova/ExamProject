package com.example.foodplanner.config;



import com.example.foodplanner.repository.UserRepository;
import com.example.foodplanner.service.FoodPlannerUserDetailsService;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ).
                authorizeHttpRequests(
                // Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // allow actuator endpoints
                        .requestMatchers( EndpointRequest.toAnyEndpoint()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers(
                                "/",
                                "/users/login",
                                "/users/register",
                                "/users/login-error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/recipes/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(
                                "/recipes/edit/**",
                                "/recipes/my-recipes",
                                "/recipes/api/owned",
                                "/recipes/owned",
                                "/picture/delete").hasRole("RECIPE_OWNER")
                        .requestMatchers(
                                "/users/api/owned",
                                "/recipes/owned",
                                "/recipes/api/owned",
                                "/recipes/edit/**",
                                "/picture/delete",
                                "/admin/**",
                                "/users/change-roles/**",
                                "/users/all",
                                "/recipes/manage/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            .loginPage("/users/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // the URL where we should POST something in order to perform the logout
                            .logoutUrl("/users/logout")
                            // where to go when logged out?
                            .logoutSuccessUrl("/")
                            // invalidate the HTTP session
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                }
        ).build();
    }




    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the mobilele users and roles
        // to representation which spring security understands.
        return new FoodPlannerUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}