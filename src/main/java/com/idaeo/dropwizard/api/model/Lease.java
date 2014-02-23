package com.idaeo.dropwizard.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author conordockry on 2/22/14
 */
@Entity
@Table(name = "Lease")
public class Lease {

    @Id
    @Column
    @Getter
    @Setter
    Long id;

    Long tenantId;

}
