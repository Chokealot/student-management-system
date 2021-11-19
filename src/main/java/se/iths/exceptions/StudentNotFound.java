package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentNotFound extends WebApplicationException {

    public StudentNotFound(Long id) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new StudentNotFoundError(id, "Student not found."))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}