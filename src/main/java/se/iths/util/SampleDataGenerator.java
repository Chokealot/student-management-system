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

        student.addSubject(englishA);
        student1.addSubject(englishA);
        student2.addSubject(englishA);
        student3.addSubject(englishA);

        student4.addSubject(astronomiB);
        student1.addSubject(astronomiB);
        student5.addSubject(astronomiB);

        student.addSubject(physicsB);
        student2.addSubject(physicsB);
        student3.addSubject(physicsB);
        student5.addSubject(physicsB);

        student.addSubject(swedishA);
        student1.addSubject(swedishA);
        student3.addSubject(swedishA);

        student.addSubject(englishB);
        student1.addSubject(englishB);
        student2.addSubject(englishB);
        student4.addSubject(englishB);

        em.persist(student);
        em.persist(student1);
        em.persist(student2);
        em.persist(student3);
        em.persist(student4);
        em.persist(student5);

        em.persist(teacher);
        em.persist(teacher1);

        em.persist(englishA);
        em.persist(englishB);
        em.persist(swedishA);
        em.persist(astronomiB);
        em.persist(physicsB);

    }
}
