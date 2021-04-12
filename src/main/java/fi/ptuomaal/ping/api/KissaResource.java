package fi.ptuomaal.ping.api;

import fi.ptuomaal.ping.entity.Kissa;
import fi.ptuomaal.ping.service.KissaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// Tämä luokka toimii controllerina kissa-päätteisille API:n-endpointeille.

@Path("kissa")
public class KissaResource {

    @Inject
    KissaService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kissa> getAllKissas() {
        return service.getAllKissas();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kissa> getKissaByName(@PathParam("name") String name) {
        return service.getKissaByName(name);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kissa getKissaById(@PathParam("id") Long id) {
        return service.getKissaById(id);
    }

    @GET
    @Path("/by-breed/{breed}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBreed(@PathParam("breed") String breed) {
        List<Kissa> result = service.getKissaByBreed(breed);
        if (result.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(result).build();
        }
    }
}
