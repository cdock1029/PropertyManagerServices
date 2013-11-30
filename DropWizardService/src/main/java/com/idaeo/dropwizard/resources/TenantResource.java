package com.idaeo.dropwizard.resources;

import com.google.common.base.Optional;
import com.idaeo.dropwizard.core.Tenant;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/tenant")
@Produces(MediaType.APPLICATION_JSON)
public class TenantResource {
    private final AtomicLong counter;
    private final String defaultName;
    private final String template;

    public TenantResource(String template, String defaultName) {
        this.counter = new AtomicLong();
        this.template = template;
        this.defaultName = defaultName;
    }


    @GET
    @Timed
    public Tenant getTenant(@QueryParam("name") Optional<String> name) {
        return new Tenant(counter.incrementAndGet(), String.format(template, name.or(defaultName)), "email@example.com");
    }
    
}
