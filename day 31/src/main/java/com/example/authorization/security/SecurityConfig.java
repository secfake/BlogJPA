package com.example.authorization.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/dashboard").hasAnyRole("ADMIN", "SALE")
                        .requestMatchers("POST","PUT","GET","/admin/user").hasRole("ADMIN")
                        .requestMatchers("/products").hasAnyRole("ADMIN", "SALE")
                        .requestMatchers("/blogs").hasAnyRole("ADMIN", "SALE","AUTHOR")
                        .requestMatchers("PUT","GET","/users").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("111")
                .roles("USER", "ADMIN")
                .build();

        UserDetails author = User.withDefaultPasswordEncoder()
                .username("author")
                .password("111")
                .roles("USER", "AUTHOR")
                .build();

        UserDetails sale = User.withDefaultPasswordEncoder()
                .username("sale")
                .password("111")
                .roles("USER", "SALE")
                .build();

        return new InMemoryUserDetailsManager(admin, author, sale);
    }



}
