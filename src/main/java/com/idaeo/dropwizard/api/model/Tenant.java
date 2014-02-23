package com.idaeo.dropwizard.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Tenant domain model object and methods
 */
@Entity
@Table(name = "Tenant")
@Data
public class Tenant {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    @Email
    private String email;

    @Column
    @NotBlank
    private String phone;

    @Column
    @NotNull
    private Integer balance;
}
