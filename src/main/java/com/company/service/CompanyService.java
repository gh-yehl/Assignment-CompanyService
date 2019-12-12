package com.company.service;

import com.company.domain.Company;
import com.company.model.CompanyDTO;
import com.company.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public void editCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);
        companyRepository.save(company);
    }

    public void saveCompany(CompanyDTO companyDTO) {
        this.editCompany(companyDTO);
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }


    public List<CompanyDTO> findAll() {
        List<Company> list = companyRepository.findAll();
        List<CompanyDTO> dtoList = new ArrayList<CompanyDTO>();
        for (Company company: list) {
            CompanyDTO companyDTO = new CompanyDTO();

            BeanUtils.copyProperties(company, companyDTO);
            dtoList.add(companyDTO);
        }
        return dtoList;
    }

    public CompanyDTO findCompanyByName(String companyName) {
        Company company = companyRepository.findCompaniesByCompanyName(companyName);
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company,companyDTO);
        return companyDTO;
    }

    public List<CompanyDTO> findCompaniesBySectorName(String sectorName) {
        List<Company> companyList = companyRepository.findCompaniesBySectorName(sectorName);
        List<CompanyDTO> dtoList = new ArrayList<CompanyDTO>();
        for (Company company: companyList) {
            CompanyDTO companyDTO = new CompanyDTO();

            BeanUtils.copyProperties(company, companyDTO);
            dtoList.add(companyDTO);
        }
        return dtoList;
    }
}
