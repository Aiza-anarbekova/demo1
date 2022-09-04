package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Student;
import peaksoft.repository.*;
import peaksoft.service.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final InstructorService instructorRepository;
    private final StudentService studentRepository;
    private final LessonService lessonRepository;
    private final CourseService courseRepository;
    private final TaskService taskRepositoryImpl;
@Autowired
    public StudentController(InstructorService instructorRepository, StudentService studentRepository, LessonService lessonRepository, CourseService courseRepository, TaskService taskRepositoryImpl) {
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.taskRepositoryImpl = taskRepositoryImpl;
    }

    @GetMapping("/allStudents/{id}")
    public String allStudents(@PathVariable Long id, Model model,
                              @ModelAttribute("course") Course course) {
        model.addAttribute("students", studentRepository.getStudentByCourseId(id));
        model.addAttribute("lessons", lessonRepository.getLessonByCourseId(id));
        model.addAttribute("instructors", instructorRepository.getInstructorByCourseId(id));
        model.addAttribute("inst", instructorRepository.getById(id));
        model.addAttribute("courses", courseRepository.getCourseByCompanyId(id));
        model.addAttribute("course", id);
        model.addAttribute("task", taskRepositoryImpl.getTaskByLessonId(id));
        return "/courses/courseInner";
    }

    @GetMapping("/{id}/new")
    public String newStudent(@PathVariable Long id, Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("comId", id);
        return "/students/newStudent";

    }

    @PostMapping("{id}/saveStudents")
    public String saveStud(@PathVariable Long id,
                           @ModelAttribute("newStudent") Student student) {
        studentRepository.addStudents(id, student);
        return "redirect:/courses/allCourses/ " + id;
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.getById(id);
        model.addAttribute("student", student);
        model.addAttribute("comId", student.getCompany().getId());
        return "students/editStudent";
    }

    @RequestMapping("/{id}/{comId}/update")
    public String updateStudent(@ModelAttribute("student") Student student
                              , @PathVariable Long id,
                                @PathVariable("comId") Long comId) {
        studentRepository.updateStudent(id, student);
        return "redirect:/courses/allCourses/ " + comId;

    }

    @RequestMapping("/{id}/{companyId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("companyId") Long id2) {
        studentRepository.deleteStudent(id);
        return "redirect:/courses/allCourses/ " + id2;
    }
}
