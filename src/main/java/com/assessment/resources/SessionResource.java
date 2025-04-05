package com.assessment.resources;

import com.assessment.api.Session;
import com.assessment.dao.SessionDAO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Path("/sessions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionResource.class);
    private final SessionDAO sessionDAO;

    public SessionResource(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @POST
    public Response createSession(@Valid Session session) {
        try {
            int id = sessionDAO.insert(session);
            LOGGER.info("Session created with ID: {}", id);
            return Response.status(Response.Status.CREATED).entity("Session created with ID: " + id).build();
        } catch (Exception e) {
            LOGGER.error("Error creating session", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating session").build();
        }
    }

    @GET
    public List<Session> getAllSessions() {
        return sessionDAO.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getSession(@PathParam("id") int id) {
        try {
            Optional<Session> session = sessionDAO.findById(id);
            if (session.isPresent()) {
                return Response.ok(session.get()).build();
            } else {
                LOGGER.warn("Session with ID {} not found", id);
                return Response.status(Response.Status.NOT_FOUND).entity("Session not found").build();
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving session", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving session").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSession(@PathParam("id") int id, @Valid Session session) {
        try {
            sessionDAO.update(id, session);
            LOGGER.info("Session with ID {} updated", id);
            return Response.ok("Session updated").build();
        } catch (Exception e) {
            LOGGER.error("Error updating session", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating session").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSession(@PathParam("id") int id) {
        try {
            sessionDAO.delete(id);
            LOGGER.info("Session with ID {} deleted", id);
            return Response.ok("Session deleted").build();
        } catch (Exception e) {
            LOGGER.error("Error deleting session", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting session").build();
        }
    }
}
