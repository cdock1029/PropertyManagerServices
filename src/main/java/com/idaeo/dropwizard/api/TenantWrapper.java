package com.idaeo.dropwizard.api;

/**
 * @author cdock
 *         Date: 2/9/14
 *         Time: 7:54 PM
 */
public class TenantWrapper {

    private Tenant tenant;

    public TenantWrapper() {

    }

    public TenantWrapper(Tenant tenant) {
        this.tenant = tenant;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
