package com.stockmarket.company_management.repository;

import com.stockmarket.company_management.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company,String> {
    Company findByCompanyCode(String companyCode);
}
