package com.idaeo.dropwizard.api;

import com.yammer.dropwizard.json.JsonSnakeCase;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Tenant domain model object and methods
 */
@JsonSnakeCase
public class Tenant {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private String building;
    private String unit;
    @NotEmpty
    private String phone;

    public Tenant(long id, String name, String email, String building, String unit, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.building = building;
        this.unit = unit;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
