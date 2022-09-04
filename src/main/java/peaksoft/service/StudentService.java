package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
@Autowired
    public StudentService(StudentRepository studentRepository, CompanyRepository companyRepository) {
        this.studentRepository = studentRepository;
    this.companyRepository = companyRepository;
}

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void addStudents(Long id, Student student) {
        Company company = companyRepository.findCompanyById(id);
        company.addStudent(student);
        student.setCompany(company);
        studentRepository.save(student);

    }

    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getStudentByCourseId(Long id) {
        return studentRepository.findStudentByCourseId(id);
    }
    public List<Student> getStudentByCompanyId(Long id) {
        return studentRepository.findStudentByCompanyId(id);
    }

    public void updateStudent(Long id, Student student) {
        Student student1 = studentRepository.findById(id).get();
        student1.setId(student.getId());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);

    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        student.setCourse(null);
        studentRepository.deleteById(id);
    }
}
