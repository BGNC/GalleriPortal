package com.bgnc.galleriportal.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RefreshToken extends BaseEntity {
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "expire_date")
    private Date expireDate;

    @ManyToOne
    private User user;

}
