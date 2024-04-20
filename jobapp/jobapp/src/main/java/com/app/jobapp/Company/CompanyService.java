package com.app.jobapp.Company;

import java.util.List;

public interface CompanyService
{

    List<Company> listAllCompanies();

    Company findCompanyById(Long id);

    boolean addCompany(Company company);

    boolean updateCompanyDetails(Long id, Company updateDetails);

    boolean deleteCompanyById(Long id);
}
