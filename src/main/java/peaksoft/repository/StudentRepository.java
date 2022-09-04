package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List <Student> findStudentByCompanyId(Long id);
    List<Student> findStudentByCourseId(Long id);

}
