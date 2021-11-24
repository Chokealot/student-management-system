package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exceptions.StudentDeletedException;
import se.iths.exceptions.StudentNotFound;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService service;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        if (teacher == null)
            throw new WebApplicationException(Response.status(Response.Status.LENGTH_REQUIRED).type(MediaType.TEXT_PLAIN_TYPE).build());
        service.createTeacher(teacher);
        return Response.created(URI.create("http://localhost:8080/student-management-system/api/v1/teachers/"+teacher.getId())).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = service.getAllTeachers();
        if (foundTeachers.isEmpty())
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
        return Response.ok(foundTeachers).build();
    }
    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        if (teacher == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Error! Cannot update object without content!").type(MediaType.APPLICATION_JSON).build());
        service.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        Teacher teacherToDelete = service.getById(id);
        if (teacherToDelete == null)
            throw new StudentDeletedException(id);
        service.deleteTeacher(teacherToDelete.getId());
        return Response.accepted("Deleted!").build();
    }

    @Path("{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher teacherFound = service.getById(id);
        if (teacherFound == null)
            throw new StudentNotFound(id);
        return Response.ok(teacherFound).build();
    }
}
