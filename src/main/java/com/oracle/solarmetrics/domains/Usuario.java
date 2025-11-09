package com.oracle.solarmetrics.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity(name = "SM_LOGIN")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Usuario implements UserDetails {

    @Id
    private String username;
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}