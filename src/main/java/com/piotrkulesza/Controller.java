package com.piotrkulesza;

import com.piotrkulesza.ghhandler.GHService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/github")
@Produces(MediaType.APPLICATION_JSON)  // Ensure JSON output
@Consumes(MediaType.APPLICATION_JSON)
public class Controller {

    @Inject
    GHService ghService;
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<RestResponse<Object>> getRepositories(@PathParam("username") String username) {
        return ghService.getRepositories(username);
    }

}
