package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findCoursesById( Long id);

    List<Course> findCourseByCompanyId(Long id);
    Course findCourseById(Long id);
}
