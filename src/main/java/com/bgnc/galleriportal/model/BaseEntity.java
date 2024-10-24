package com.bgnc.galleriportal.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false,name = "create_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createTime;
}
