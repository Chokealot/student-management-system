package se.iths.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentDeletedException extends WebApplicationException {
    public StudentDeletedException(Long id) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new StudentDeletedExceptionError(id, "The student you are trying to delete does not exist!"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
