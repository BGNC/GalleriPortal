package com.bgnc.galleriportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address extends BaseEntity{

    @Column(name = "street",nullable = false)
    @NotEmpty(message = "Street field is required")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @Column(name = "district",nullable = false)
    @NotEmpty(message = "District field is required")
    @Size(max = 50, message = "District must not exceed 50 characters")
    private String district;


    @Column(name = "city",nullable = false)
    @NotEmpty(message = "City field is required")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Column(name = "neighborhood" , nullable = false)
    @NotEmpty(message = "Neighboord field is required")
    @Size(max = 50, message = "Neighborhood must not exceed 50 characters")
    private String neighborhood;
}
