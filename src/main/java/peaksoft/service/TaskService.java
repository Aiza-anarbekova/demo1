package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepositoryImpl;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepositoryImpl taskRepository;
    private final LessonRepository lessonRepository;
@Autowired
    public TaskService(TaskRepositoryImpl taskRepository, LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
    this.lessonRepository = lessonRepository;
}

    public List<Task> getAllTask(Long id) {
        return taskRepository.findTasksById(id);
    }


    public void addTask(Long id, Task task) {
        Lesson lesson = lessonRepository.findLessonById(id);
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
    }


    public Task getById(Long id) {
        return   taskRepository.findTaskById(id);

    }


    public List<Task> getTaskByLessonId(Long id) {
        return taskRepository.
                findTaskByLessonId(id);
    }


    public void updateTask(Long id, Task task) {
        Task task1 = taskRepository.findTaskById(id);
        task1.setId(task.getId());
        task1.setName(task.getName());
        task1.setDeadline(task.getDeadline());
        task1.setTask(task.getTask());
        taskRepository.save(task1);

    }

    public void deleteTask(Long id) {
        Task task =taskRepository.findTaskById(id);
        task.setLesson(null);
        taskRepository.deleteById(id);

    }
}
