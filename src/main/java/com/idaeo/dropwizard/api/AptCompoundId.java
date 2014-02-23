package com.idaeo.dropwizard.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author conordockry on 2/23/14
 */
@Embeddable
@NoArgsConstructor
public class AptCompoundId implements Serializable {

    @NaturalId
    @Column(name = "building_id")
    @Getter @Setter
    Long buildingId;

    @NaturalId
    @Column
    @Getter @Setter
    String number;
}
