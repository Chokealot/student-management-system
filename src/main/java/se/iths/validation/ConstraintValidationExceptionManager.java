package se.iths.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ValidationError> errors = exception.getConstraintViolations().stream()
                .map(this::toValidationError)
                .collect(
                        Collectors.toList());

        return Response.status(Response.Status.BAD_REQUEST).entity(errors)
                .type(MediaType.APPLICATION_JSON).build();
    }

    private ValidationError toValidationError(ConstraintViolation constraintViolation) {
        ValidationError error = new ValidationError();
        error.setPath(constraintViolation.getPropertyPath().toString());
        error.setMessage(constraintViolation.getMessage());
        return error;
    }
}