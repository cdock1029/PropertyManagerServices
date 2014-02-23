package com.idaeo.dropwizard.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * @author cdock Date: 1/26/14
 */
@Entity
@Table(name = "Building")
@Data
public class Building {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String address;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotBlank
    private String zip;
}
