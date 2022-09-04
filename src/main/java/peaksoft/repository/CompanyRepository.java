package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company , Long> {
//    void updateCompany(Long id, Company company);
    Company findCompanyById(Long id);

}
