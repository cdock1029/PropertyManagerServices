package com.idaeo.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("")
public class HelloWorldResource {

    @GET
    @Path("/hello/{name}")
    public String getHello(@PathParam("name") String name) {
        return "Hello there, " + name + "!";
    }
    
    @GET
    @Path("/")
    public String getHelloWorld() {
        return "Hello world!";
    }
}
