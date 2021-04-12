package fi.ptuomaal.ping.api;

import fi.ptuomaal.ping.entity.Pong;
import fi.ptuomaal.ping.service.PongService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// Tämä luokka toimii controllerina ping-päätteisille API:n-endpointeille.

@Path("ping")
public class PongResource {

    @Inject
    PongService service;

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    public String ping(@PathParam("name") String name) {
        return service.getPongsAsString(name);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pong getPong(@PathParam("id") Long id) {
        return service.getPong(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pong postPong(String name) {
        return service.postPong(name);
    }

    @DELETE
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deletePong(@PathParam("id") Long id) {
        return service.deletePong(id);
    }
}
