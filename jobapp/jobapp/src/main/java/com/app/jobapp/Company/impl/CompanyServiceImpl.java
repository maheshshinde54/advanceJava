package com.app.jobapp.Company.impl;

import com.app.jobapp.Company.CompanyRespository;
import com.app.jobapp.Company.Company;
import com.app.jobapp.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService
{
    Long nextId = 1L;
    private final CompanyRespository companyRespository;

    public CompanyServiceImpl(CompanyRespository companyRespository)
    {
        this.companyRespository = companyRespository;
    }

    @Override
    public List<Company> listAllCompanies()
    {
        return companyRespository.findAll();
    }

    @Override
    public Company findCompanyById(Long id)
    {
        return companyRespository.findById(id).orElse(null);
    }

    @Override
    public boolean addCompany(Company company)
    {

        company.setId(nextId++);
        companyRespository.save(company);
        return true;
    }

    @Override
    public boolean updateCompanyDetails(Long id, Company updateDetails)
    {

        Optional<Company> companyObject = companyRespository.findById(id);
        if(companyObject.isPresent())
        {
            Company companynew = companyObject.get();
            companynew.setDescription(updateDetails.getDescription());
            companynew.setName(updateDetails.getName());
            companynew.setJobs(updateDetails.getJobs());
            companyRespository.save(companynew);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteCompanyById(Long id)
    {
        companyRespository.deleteById(id);
        return true;
    }
}
