package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager em;

    public Subject createSubject(Subject subject) {
        em.persist(subject);
        return subject;
    }

    public Subject findById(Long id) {
        return em.find(Subject.class, id);
    }

    public Subject updateSubject(Subject subject) {
        em.merge(subject);
        return subject;
    }

    public List<Subject> getAllSubjects() {
        return em.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    public Subject getById(Long id) {
        return em.find(Subject.class, id);
    }

    public Subject addStudent(Long id, Student student) {
        Subject subject = em.find(Subject.class, id);
        subject.addStudentToSubject(student);
        em.merge(subject);
        return subject;
    }

    public void deleteSubject(Long id) {
        Subject subjectToDelete = em.find(Subject.class, id);
        em.remove(subjectToDelete);
    }

}
