package com.idaeo.dropwizard.resources;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.idaeo.dropwizard.api.Building;
import com.yammer.metrics.annotation.Timed;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Date: 2/1/14
 * Time: 6:37 PM
 */
@Path("/buildings")
@Produces(MediaType.APPLICATION_JSON)
public class BuildingResource {

    private final DynamoDBMapper mapper;

    public BuildingResource(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @GET
    @Timed
    public List<Building> listBuildings() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Building> scanList = mapper.scan(Building.class, scanExpression);
        scanList.loadAllResults();
        return scanList;
    }

    @GET
    @Timed
    @Path("/{name}")
    public Building getBuilding(@PathParam("name") String name) {
        WebApplicationException ex = new WebApplicationException(Response.Status.NOT_FOUND);
        if (StringUtils.isEmpty(name)) {
            throw ex;
        }
        Building building = mapper.load(Building.class, name);
        if (building == null) {
            throw ex;
        }
        return building;
    }
}
