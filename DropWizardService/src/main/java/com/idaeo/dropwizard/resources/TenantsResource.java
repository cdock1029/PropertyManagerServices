package com.idaeo.dropwizard.resources;

import com.google.common.base.Optional;
import com.idaeo.dropwizard.api.Tenant;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/tenants")
@Produces(MediaType.APPLICATION_JSON)
public class TenantsResource {
    private final AtomicLong counter;
    private final String defaultName;
    private final String template;

    private static final List<Tenant> tenants;

    static {
        tenants = new ArrayList<>();
        Tenant conor, amanda, bill;

        conor = new Tenant(1, "Conor Dockry", "conordockry@gmail.com", "3307270027");
        amanda = new Tenant(2, "Amanda Becerra", "aebecerra28@gmail.com", "5557271414");
        bill = new Tenant(3, "William Brasky", "billb@gmail.com", "5558675309");

        tenants.add(conor);
        tenants.add(amanda);
        tenants.add(bill);

    }

    public TenantsResource(String template, String defaultName) {
        this.counter = new AtomicLong();
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Timed
    public List<Tenant> listTenants() {
        return tenants;
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
        try {
        if (aLong != null) {
            return tenants.get(aLong.intValue() - 1);
        } else {
            throw ex;
        }
        } catch (IndexOutOfBoundsException e) {
            throw ex;
        }
    }

//    public Tenant getTenant(@PathParam("id") Optional<String> name) {
//        return new Tenant(counter.incrementAndGet(), String.format(template, name.or(defaultName)), "email@example.com", );
//    }
    
}
