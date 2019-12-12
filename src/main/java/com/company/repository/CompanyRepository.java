package com.company.repository;

import com.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    public Company findById(long id);
    public Company findCompaniesByCompanyName(String companyName) ;
    public List<Company> findCompaniesBySectorName(String sectorName) ;
}
