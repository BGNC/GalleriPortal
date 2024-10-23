package com.bgnc.galleriportal.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {


    @Column(unique = true, nullable = false,name = "username")
    @NotEmpty(message = "Kullanıcı Adınızı Boş Geçmeyiniz")
    private String username;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Şifre alanınızı  boş geçmeyiniz")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
