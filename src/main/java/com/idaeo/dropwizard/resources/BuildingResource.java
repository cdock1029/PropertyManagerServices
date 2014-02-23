package com.idaeo.dropwizard.resources;

import com.idaeo.dropwizard.api.model.Building;
import com.idaeo.dropwizard.api.model.wrapper.BuildingListWrapper;
import com.idaeo.dropwizard.api.model.wrapper.BuildingWrapper;
import com.idaeo.dropwizard.data.BuildingDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
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
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class BuildingResource {

    private final BuildingDAO dao;

    @GET
    @Timed
    @UnitOfWork
    public BuildingListWrapper listBuildings() {
        List<Building> all = dao.findAll();
        BuildingListWrapper buildingListWrapper = new BuildingListWrapper();
        buildingListWrapper.setBuildings(all);
        return buildingListWrapper;
    }

    @GET
    @Timed
    @Path("/{id}")
    @UnitOfWork
    public BuildingWrapper getBuilding(@PathParam("id") Long id) {
        Building building = dao.findById(id);
        if (null == building) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        BuildingWrapper buildingWrapper = new BuildingWrapper();
        buildingWrapper.setBuilding(building);
        return buildingWrapper;
    }

    @POST
    @Timed
    @UnitOfWork
    public Response createBuildings(@Valid BuildingListWrapper buildingListWrapper) {
        dao.batchCreate(buildingListWrapper.getBuildings());
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response deleteBuilding(@PathParam("id") Long id) {
        dao.deleteById(id);
        return Response.ok().build();
    }
}
