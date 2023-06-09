package tech.escalab.apicourse.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import tech.escalab.apicourse.model.entity.Course;
import tech.escalab.apicourse.model.entity.Institution;

import java.util.List;

public class CourseCriteriaRepositoryImpl implements CourseCriteriaRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Course> getAllCourses() {
        /* SELECT * FROM courses; */
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        criteriaQuery.select(courseRoot);
        List<Course> result = entityManager.createQuery(criteriaQuery).getResultList();

        return result;
    }

    @Override
    public Course getCourseByName(String name) {
        /* SELECT * FROM courses WHERE name = 'name'; */

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        criteriaQuery.select(courseRoot).where(criteriaBuilder.equal(courseRoot.get("name"), name));
        Course result = entityManager.createQuery(criteriaQuery).getSingleResult();

        return result;
    }

    @Override
    public Course getCourseByInstitution(String institution) {
        /*
            SELECT * FROM courses c JOIN institutions i ON c.institution_id = i.institution_id
         */

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        Join<Course, Institution> relationship = courseRoot.join("institution");
        criteriaQuery.select(courseRoot).where(criteriaBuilder.equal(relationship.get("name"), institution));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}


