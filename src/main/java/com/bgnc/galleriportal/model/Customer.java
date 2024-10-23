package com.bgnc.galleriportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity {

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name")
    private String lastName;


    @NotBlank(message = "TCKN cannot be blank")
    @Column(name = "tckn")
    @Pattern(regexp = "^[0-9]{11}$", message = "TCKN must be exactly 11 digits")
    private String tckn;

    @NotNull(message = "Birth date cannot be null")
    @Column(name = "birth_of_date")
    @Past(message = "Birth date must be in the past")
    private Date birthOfDate;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;
}
