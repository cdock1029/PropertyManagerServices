package com.idaeo.dropwizard.api;

import java.util.List;

/**
 * @author cdock
 *         Date: 2/9/14
 *         Time: 7:58 PM
 */
public class TenantListWrapper {
    private List<Tenant> tenants;

    public TenantListWrapper(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }
}
