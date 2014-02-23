package com.idaeo.dropwizard.api.model.wrapper;

import com.idaeo.dropwizard.api.model.Tenant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cdock Date: 2/9/14
 */
@Data
public class TenantListWrapper {

    @NotNull
    private List<Tenant> tenants;
}
