package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

@Autowired
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    this.companyRepository = companyRepository;
}

    public List<Course> getAllCourses(Long id) {
        return courseRepository.findCoursesById(id);
    }


    public void addCourse(Long id, Course course) {
        Company company = companyRepository.findCompanyById(id);
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
    }


    public List<Course> getCourseByCompanyId(Long id) {
        return courseRepository.findCourseByCompanyId(id);

    }


    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not fount"));
    }


    public void updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).get();
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        course1.setDuration(course.getDuration());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setImage(course.getImage());
        courseRepository.save(course1);
    }


    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
