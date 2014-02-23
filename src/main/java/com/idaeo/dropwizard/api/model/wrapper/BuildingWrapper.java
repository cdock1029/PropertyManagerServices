package com.idaeo.dropwizard.api.model.wrapper;

import com.idaeo.dropwizard.api.model.Building;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author conordockry on 2/23/14
 */
@Data
public class BuildingWrapper {

    @NotNull
    private Building building;
}
