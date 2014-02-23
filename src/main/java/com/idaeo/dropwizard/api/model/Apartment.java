package com.idaeo.dropwizard.api.model;

import com.idaeo.dropwizard.api.AptCompoundId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author conordockry on 2/22/14
 */
@Entity
@Table(name = "Apartment")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AptCompoundId.class)
public class Apartment {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    @Getter @Setter
    private Building building;

    @Getter @Setter
    private String number;
}
