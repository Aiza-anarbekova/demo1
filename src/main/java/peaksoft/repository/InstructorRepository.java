package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Instructor;

import java.util.List;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    List<Instructor> findInstructorsByCompanyId(Long id);
    Instructor findInstructorsById(Long id);
}
