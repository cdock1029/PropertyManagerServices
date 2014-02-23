package com.idaeo.dropwizard.api.model.wrapper;

import com.idaeo.dropwizard.api.model.Lease;
import com.idaeo.dropwizard.api.model.Tenant;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cdock Date: 2/9/14
 */
@Data
public class TenantWrapper {

    @NotNull
    private Tenant tenant;

    private List<Lease> leases;
}
