package com.oracle.solarmetrics.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class UsuarioConfiguration {

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/cliente").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                    .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout()
                .permitAll()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
