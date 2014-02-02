package com.idaeo.dropwizard.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.idaeo.dropwizard.api.Tenant;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Tenant getTenant(@PathParam("id") String id) {
        WebApplicationException ex = new WebApplicationException(Response.Status.NOT_FOUND);
        Long aLong;
        try {
            aLong = Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw ex;
        }
        Tenant tenant = null;
        if (aLong != null) {
            tenant = mapper.load(Tenant.class, aLong);
            if (tenant == null) {
                throw ex;
            }
        } else {
            throw ex;
        }
        return tenant;
    }
}
