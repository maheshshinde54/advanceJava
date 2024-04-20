package com.app.jobapp.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController
{
    private CompanyService companyService;

    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> listAllCompanies()
    {
        return ResponseEntity.ok(companyService.listAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id)
    {
        Company findByIdResponce = companyService.findCompanyById(id);
        if (findByIdResponce != null)
            return new ResponseEntity<>(findByIdResponce, HttpStatus.OK);
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company)
    {
        companyService.addCompany(company);

        return ResponseEntity.ok("Company added successfully");

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyDetails(@PathVariable Long id, @RequestBody Company updateDetails)
    {
        boolean updateStatus = companyService.updateCompanyDetails(id, updateDetails);
        if (updateStatus)
            return ResponseEntity.ok("Company details are updated successfully");
        else
            return new ResponseEntity<>("Error while updating company", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean deleteStatus = companyService.deleteCompanyById(id);
        if (deleteStatus)
            return ResponseEntity.ok("Company deleted successfully");
        else
            return new ResponseEntity<>("Error while deleting company", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
