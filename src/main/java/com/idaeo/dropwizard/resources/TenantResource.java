package com.idaeo.dropwizard.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.idaeo.dropwizard.api.Tenant;
import com.idaeo.dropwizard.api.TenantListWrapper;
import com.idaeo.dropwizard.api.TenantWrapper;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Path("/tenants")
@Produces(MediaType.APPLICATION_JSON)
public class TenantResource {
    private final DynamoDBMapper mapper;

    public TenantResource(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @GET
    @Timed
    public TenantListWrapper listTenants() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Tenant> scanList = mapper.scan(Tenant.class, scanExpression);
        scanList.loadAllResults();
        return new TenantListWrapper(scanList);
    }

    @GET
    @Timed
    @Path("/{name}")
    public TenantWrapper getTenant(@PathParam("name") String name) {
        Tenant tenant = mapper.load(Tenant.class, name);
        if (tenant == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new TenantWrapper(tenant);
    }

    @POST
    @Timed
    public Response createTenant(@Valid TenantWrapper tenantWrapper) {
        if (tenantWrapper != null && tenantWrapper.getTenant() != null) {
            Tenant loadTenant = mapper.load(Tenant.class, tenantWrapper.getTenant().getName());
            if (loadTenant != null) {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }
            mapper.save(tenantWrapper.getTenant());
            return Response.created(UriBuilder.fromResource(TenantResource.class).build(tenantWrapper.getTenant().getName())).build();
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Timed
    @Path("/{name}")
    public Response deleteTenant(@PathParam("name") String name) {
        Tenant tenant = mapper.load(Tenant.class, name);
        if (tenant == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        mapper.delete(tenant);
        return Response.ok().build();
    }

    public Response updateTenant() {
        return null;
    }
}
