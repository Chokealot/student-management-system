package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;


@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService service;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        if (subject == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).build());
        service.createSubject(subject);
        return Response.created(URI.create("http://localhost:8080/student-management-system/api/v1/subjects/"+subject.getId())).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = service.getAllSubjects();
        if (foundSubjects.isEmpty())
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Subject subject) {
        if (subject == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Error! Cannot update object without content!").type(MediaType.APPLICATION_JSON).build());
        service.updateSubject(subject);
        return Response.ok().build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        Subject foundSubject = service.findById(id);
        if (foundSubject == null)
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        service.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("{id}")
    @GET
    public Response getSubjectById(@PathParam("id") Long id) {
        Subject foundSubject = service.findById(id);
        if (foundSubject == null)
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        return Response.ok(foundSubject).build();
    }
}
