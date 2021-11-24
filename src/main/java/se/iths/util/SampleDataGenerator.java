package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.StudentService;
import se.iths.service.TeacherService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager em;

    @Inject
    StudentService studentService;

    @Inject
    TeacherService teacherService;


    @PostConstruct
    public void generateData() {

        Student student = new Student("Casper", "Konyi", "casper@konyi.com","075757575");
        Student student1 = new Student("Lisa", "Nilsson", "lisa@nilsson.com","076464125");
        Student student2 = new Student("Felix", "Renholdt","felix@msn.com","07646411");
        Student student3 = new Student("Sara", "Karlsson","sara@outlook.com","064626165");
        Student student4 = new Student("Erika", "Göransson","erika@iths.se","074626261");
        Student student5 = new Student("Viktor","Eriksson","viktor@yahoo.se","079912341");

        Teacher teacher = new Teacher("Henrik Gustavsson");
        Teacher teacher1 = new Teacher("Lennart Olsson");


        Subject englishA = new Subject("English - A", teacher);
        Subject astronomiB  = new Subject("Astronomi - B", teacher);
        Subject physicsB = new Subject("Physics - B", teacher);

        Subject swedishA = new Subject("Swedish - A", teacher1);
        Subject englishB = new Subject("English - B", teacher1);

        englishA.addStudentToSubject(student);
        englishA.addStudentToSubject(student1);
        englishA.addStudentToSubject(student3);
        englishA.addStudentToSubject(student4);

        astronomiB.addStudentToSubject(student4);
        astronomiB.addStudentToSubject(student5);

        physicsB.addStudentToSubject(student1);
        physicsB.addStudentToSubject(student2);
        physicsB.addStudentToSubject(student5);

        swedishA.addStudentToSubject(student4);
        swedishA.addStudentToSubject(student4);
        swedishA.addStudentToSubject(student1);

        englishB.addStudentToSubject(student3);
        englishB.addStudentToSubject(student5);
        englishB.addStudentToSubject(student);

        studentService.createStudent(student);
        studentService.createStudent(student1);
        studentService.createStudent(student2);
        studentService.createStudent(student3);
        studentService.createStudent(student4);
        studentService.createStudent(student5);

        teacherService.createTeacher(teacher);
        teacherService.createTeacher(teacher1);

        em.persist(englishA);
        em.persist(englishB);
        em.persist(swedishA);
        em.persist(astronomiB);
        em.persist(physicsB);

    }
}
