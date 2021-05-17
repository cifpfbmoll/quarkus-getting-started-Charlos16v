package org.acme.getting.started;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {
    @Inject
    GreetingService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/car")
    public Response createCar(Car car) {
        return Response.ok(car).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/car")
    public Car getCar() {
        return new Car("Volkswagen", "Golf G60", 180);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @ConfigProperty(name = "greetings.message")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message;
    }
}