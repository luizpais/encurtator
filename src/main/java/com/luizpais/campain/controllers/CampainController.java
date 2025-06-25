package com.luizpais.campain.controllers;

import com.luizpais.campain.model.CampainDTO;
import com.luizpais.campain.services.CampainService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/campains")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampainController {

    @Inject
    CampainService service;

    @POST
    public Response createCampain(CampainDTO dto) {
        service.createCampain(dto.getName(), dto.getDescription(), dto.getStartDate(), dto.getEndDate());
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<CampainDTO> getAllCampains() {
        return service.getAllCampains();
    }

    @GET
    @Path("/{id}")
    public CampainDTO getCampainById(@PathParam("id") Long id) {
        return service.findCampainById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateCampain(@PathParam("id") Long id, CampainDTO dto) {
        dto.setId(id);
        service.updateCampain(dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCampain(@PathParam("id") Long id) {
        service.deleteCampain(id);
        return Response.noContent().build();
    }
}