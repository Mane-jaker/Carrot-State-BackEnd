package com.example.carrotstatebackend.security;

import com.example.carrotstatebackend.security.JWTFilters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    @Qualifier("AgentDetails")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("AdminDetails")
    private UserDetailsService adminDetailsService;

    @Autowired
    @Qualifier("RealStateDetails")
    private UserDetailsService realStateDetailsService;

    @Autowired
    @Qualifier("ClientDetails")
    private UserDetailsService clientDetailsService;

    @Autowired
    private JWTAuthorizationFIlter jwtAuthorizationFIlter;

    @Bean
    SecurityFilterChain agentFilterChain(HttpSecurity http,
                                         @Qualifier("AgentAuth") AuthenticationManager authManager,
                                         @Qualifier("AdminAuth") AuthenticationManager adminAuth,
                                         @Qualifier("RealStateAuth") AuthenticationManager realStateAuth,
                                         @Qualifier("ClientAuth") AuthenticationManager clientAuth
                                         ) throws Exception {

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login/agent");

        JWTAdminAuthenticationFilter jwtAdminAuthenticationFilter = new JWTAdminAuthenticationFilter();
        jwtAdminAuthenticationFilter.setAuthenticationManager(adminAuth);
        jwtAdminAuthenticationFilter.setFilterProcessesUrl("/login/admin");

        JWTRealStateAuthenticationFilter jwtRealStateAuthenticationFilter = new JWTRealStateAuthenticationFilter();
        jwtAdminAuthenticationFilter.setAuthenticationManager(realStateAuth);
        jwtAdminAuthenticationFilter.setFilterProcessesUrl("/login/real_state");

        JWTClientAuthenticationFilter jwtClientAuthenticationFilter = new JWTClientAuthenticationFilter();
        jwtAdminAuthenticationFilter.setAuthenticationManager(clientAuth);
        jwtAdminAuthenticationFilter.setFilterProcessesUrl("/login/client");


        return http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilter(jwtAdminAuthenticationFilter)
                .addFilter(jwtRealStateAuthenticationFilter)
                .addFilter(jwtClientAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFIlter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean("AgentAuth") @Primary
    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean("AdminAuth")
    public AuthenticationManager adminAuthManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(adminDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean("RealStateAuth")
    public AuthenticationManager realStateAuthManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(realStateDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean("ClientAuth")
    public AuthenticationManager clientAuthManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(clientDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println("pass: " + new BCryptPasswordEncoder().encode("lolo123"));
    }

}
