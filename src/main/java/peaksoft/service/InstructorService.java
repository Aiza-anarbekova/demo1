package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
@Autowired
    public InstructorService(InstructorRepository instructorRepository, CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    this.companyRepository = companyRepository;
}


//    public List<Instructor> getAllInstructor(Long id) {
//        return instructorRepository.findInstructorsById(id);
//    }


    public void addInstructor(Long id, Instructor instructor) {
        Company company = companyRepository.findCompanyById(id);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        instructorRepository.save(instructor);

    }

    public Instructor getById(Long id) {
        return instructorRepository.findInstructorsById(id);

    }

    public List<Instructor> getInstructorByCompanyId(Long id) {

        return instructorRepository.findInstructorsByCompanyId(id);
    }


    public List<Instructor> getInstructorByCourseId(Long id) {
        return courseRepository.findById(id).orElseThrow(
                ()->new RuntimeException("not fount")
        ).getInstructors();
    }

    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = instructorRepository.findById(id).get();
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructorRepository.save(instructor1);

    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }


    public void assignInstructorToCourse(Long instrId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instrId).get();
        Course course = courseRepository.findById(courseId).get();
        instructor.addCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
    }
}
