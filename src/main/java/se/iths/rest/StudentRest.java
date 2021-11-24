package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exceptions.StudentDeletedException;
import se.iths.service.StudentService;
import se.iths.exceptions.StudentNotFound;
import se.iths.exceptions.StudentsNotFound;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService service;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        if (student == null)
            throw new WebApplicationException(Response.status(Response.Status.LENGTH_REQUIRED).type(MediaType.TEXT_PLAIN_TYPE).build());
            service.createStudent(student);
            return Response.created(URI.create("http://localhost:8080/student-management-system/api/v1/students/"+student.getId())).build();
    }

    @Path("")
    @GET
    public Response getAllStudents()    {
        List<Student> foundStudents = service.getAllStudents();
        if (foundStudents.isEmpty())
            throw new StudentsNotFound();
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        if (student == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Error! Cannot update object without content!").type(MediaType.APPLICATION_JSON).build());
        service.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("/lastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudents = service.findByLastName(lastName);
        if (foundStudents.isEmpty())
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("ERROR: No students with lastname "+lastName+" was found!").type(MediaType.APPLICATION_JSON).build());
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Student studentToDelete = service.getById(id);
        if (studentToDelete == null)
            throw new StudentDeletedException(id);
        service.deleteStudent(studentToDelete.getId());
        return Response.accepted("Deleted!").build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student foundStudent = service.getById(id);
        if (foundStudent == null)
            throw new StudentNotFound(id);
        return Response.ok(foundStudent).build();
    }
}
