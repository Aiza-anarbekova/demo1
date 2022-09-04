package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Instructor;
import peaksoft.model.Lesson;

import java.util.List;
@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findLessonByCourseId(Long id);
    Lesson findLessonById(Long id);

}
