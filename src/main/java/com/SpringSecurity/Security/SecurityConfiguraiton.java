package com.SpringSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.plaf.PanelUI;

@Configuration
@EnableWebSecurity
public class SecurityConfiguraiton {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/home").permitAll();
                       registry.requestMatchers("/admin/**").hasRole("ADMİN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser = User.builder()
                .username("umut")
                .password("$2a$12$zGQ/3SBG2ZFbNj3tJfQRL.0EctTmYuciBaYWCeIblnA1y/Br.Pu8y")
                .roles("USER")
                .build();
        UserDetails adminUser =User.builder()
                .username("apo")
                .password("$2a$12$4wo7e/hRLy.GqWjwHlubce5iAMpMq4.93cjAVlsLSPhxRba8keOL.")
                .roles("ADMİN","USER")
                .build();
        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
