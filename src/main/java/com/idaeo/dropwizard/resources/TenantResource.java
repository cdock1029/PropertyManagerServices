package com.idaeo.dropwizard.resources;

import com.idaeo.dropwizard.api.model.Tenant;
import com.idaeo.dropwizard.api.model.wrapper.TenantListWrapper;
import com.idaeo.dropwizard.api.model.wrapper.TenantWrapper;
import com.idaeo.dropwizard.data.TenantDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tenants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TenantResource {

    private final TenantDAO dao;

    @GET
    @Timed
    @UnitOfWork
    public TenantListWrapper listTenants() {
        List<Tenant> all = dao.findAll();
        TenantListWrapper tenantListWrapper = new TenantListWrapper();
        tenantListWrapper.setTenants(all);
        return tenantListWrapper;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public TenantWrapper getTenant(@PathParam("id") Long id) {
        Tenant tenant = dao.findById(id);
        if (tenant == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        TenantWrapper tenantWrapper = new TenantWrapper();
        tenantWrapper.setTenant(tenant);
        return tenantWrapper;
    }

//    @POST
//    @Timed
//    @UnitOfWork
//    public Response createTenant(@Valid TenantWrapper tenantWrapper) {
//        if (tenantWrapper.getTenant() != null) {
//            Long id = dao.create(tenantWrapper.getTenant());
//            return Response.created(UriBuilder.fromResource(TenantResource.class).build(id)).build();
//        } else {
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//        }
//    }
    @POST
    @Timed
    @UnitOfWork
    public Response createTenants(@Valid TenantListWrapper tenantListWrapper) {
        List<Tenant> tenants = tenantListWrapper.getTenants();
        if (tenants != null) {
            dao.batchCreate(tenants);
            return Response.ok().build();
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response deleteTenant(@PathParam("id") Long id) {
        dao.deleteById(id);
        return Response.ok().build();
    }
}
