package com.idaeo.dropwizard.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.idaeo.dropwizard.api.Tenant;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
    public List<Tenant> listTenants() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Tenant> scanList = mapper.scan(Tenant.class, scanExpression);
        scanList.loadAllResults();
        return scanList;
    }

    @GET
    @Timed
    @Path("/{id}")
    public Tenant getTenant(@PathParam("id") Long id) {
        Tenant tenant = mapper.load(Tenant.class, id);
        if (tenant == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tenant;
    }

    @POST
    @Timed
    public Response createTenant(@Valid Tenant tenant) {
        mapper.save(tenant);
        return Response.created(UriBuilder.fromResource(TenantResource.class).build(tenant.getId())).build();
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public Response deleteTenant(@PathParam("id") Long id) {
        Tenant tenant = mapper.load(Tenant.class, id);
        if (tenant == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        mapper.delete(tenant);
        return Response.ok().build();
    }
}
