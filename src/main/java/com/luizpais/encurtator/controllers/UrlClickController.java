package com.luizpais.encurtator.controllers;

import com.luizpais.encurtator.model.UrlMappingDTO;
import com.luizpais.encurtator.services.UrlShortenerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import java.net.URI;

@Path("/u")
public class UrlClickController {

    @Inject
    UrlShortenerService service;

    @GET
    @Path("/{shortCode}")
    public Response redirect(@PathParam("shortCode") String shortCode, @Context HttpHeaders headers) {
        String ip = headers.getHeaderString("X-Forwarded-For");
        String userAgent = headers.getHeaderString("User-Agent");
        URI target = service.handleRedirect(shortCode, ip, userAgent);
        return Response.status(302).location(target).build();
    }

    @POST
    @Path("/shorten")
    @Produces("application/json")
    public Response shortenUrl(@RequestBody(ref = "originalUrl") UrlMappingDTO originalUrl) {
        String shortCode = service.shortenUrl(originalUrl);
        return Response.ok("{\"shortCode\":\"" + shortCode + "\"}").build();
    }

  // @POST
    @Path("/shorten/campaign")
    @Produces("application/json")
    public Response shortenUrlWithCampaign(@RequestBody(ref = "campaignUrl") UrlMappingDTO campaignUrl) {
        String shortCode = service.shortenUrlWithCampaign(campaignUrl);
        return Response.ok("{\"shortCode\":\"" + shortCode + "\"}").build();
    }

}