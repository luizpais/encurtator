package com.luizpais.encurtator.infrastructure;


import com.luizpais.encurtator.model.CampainDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/campains")
@RegisterRestClient
public interface CampainerClient {

    @GET
    @Path("/{id}")
    CampainDTO getById(String id);
}