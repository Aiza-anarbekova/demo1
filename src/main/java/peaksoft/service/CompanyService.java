package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import java.util.List;
@Service
public class CompanyService {
    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }


    public List<Company> getAllCompanies() {
        return repository.findAll();
    }


    public void updateCompany(Long id, Company company) {
        Company company1 = repository.findCompanyById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        repository.save(company1);
    }

    public void save(Company company) {
        repository.save(company);
    }


    public Company getById(Long id) {
        return repository.findCompanyById(id);
    }


    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }
}
