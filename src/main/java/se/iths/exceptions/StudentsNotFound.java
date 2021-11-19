package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentsNotFound extends WebApplicationException {
    public StudentsNotFound() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new StudentsNotFoundError("Students not found."))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
