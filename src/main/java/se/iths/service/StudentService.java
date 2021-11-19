package se.iths.service;

import se.iths.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager em;

    public Student createStudent(Student student) {
        em.persist(student);
        return student;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return em.createQuery("select s from Student s", Student.class).getResultList();
    }

    public Student updateStudent(Student student) {
        em.merge(student);
        return student;
    }

    public List<Student> findByLastName(String lastName) {
        List<Student> foundStudents = em.createQuery("select s from Student s where s.lastName = '"+lastName+"'", Student.class).getResultList();
        return foundStudents;
    }

    public void deleteStudent(Long id) {
        Student studentToDelete = em.find(Student.class, id);
        em.remove(studentToDelete);
    }

    public Student getById(Long id) {
        return em.find(Student.class, id);
    }
}
