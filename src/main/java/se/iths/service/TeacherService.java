
package se.iths.service;

import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager em;

    public Teacher createTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher;
    }

    public Teacher getById(Long id) {
        return em.find(Teacher.class, id);
    }

    public List<Teacher> getAllTeachers() {
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher updateTeacher(Teacher teacher) {
        em.merge(teacher);
        return teacher;
    }
    public void deleteTeacher(Long id) {
        Teacher teacher = em.find(Teacher.class, id);
        em.remove(teacher);
    }

}
