package com.bgnc.galleriportal.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest  {
    @NotEmpty(message = "Kullanıcı Adınızı Boş Geçmeyiniz")
    private String username;

    @NotEmpty(message = "Şifre alanınızı  boş geçmeyiniz")
    private String password;

}
