package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.admin.service.AdminUserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AdminUserService adminUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/contacts")
                        .defaultSuccessUrl("contacts")
                        .failureUrl("login?error=true"))

                // TODO CustomUserDetailServiceを実装しないといけないかも(implements UserDetailService)
                // AdminUserService#findAdminUserByEmail()を移設するかもしれない
                .userDetailsService((UserDetailsService) adminUserService);

        return http.build();
    }

}
