package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.StudentRepository;

import java.util.List;

@Service
public class LessonService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
@Autowired
    public LessonService(StudentRepository studentRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    this.courseRepository = courseRepository;
}

    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public void addLesson(Long id, Lesson lesson) {
        Course course = courseRepository.findCourseById(id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);

    }


    public Lesson getById(Long id) {
        return lessonRepository.findById(id).get();
    }


    public List<Lesson> getLessonByCourseId(Long id) {
        return lessonRepository.findLessonByCourseId(id);
    }

    public void updateLesson(Long id,Lesson lesson) {
        Lesson lesson1=lessonRepository.findById(id).get();
        lesson1.setLessonName(lesson.getLessonName());
        lesson1.setId(lesson.getId());
        lessonRepository.save(lesson1);

    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.setCourse(null);
        lessonRepository.deleteById(id);
    }
}
