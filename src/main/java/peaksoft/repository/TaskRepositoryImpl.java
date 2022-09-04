package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Task;

import java.util.List;
@Repository
public interface TaskRepositoryImpl extends JpaRepository<Task,Long> {
    List<Task> findTasksById(Long id);
    List<Task> findTaskByLessonId(Long id);
    Task findTaskById(Long id);

}
