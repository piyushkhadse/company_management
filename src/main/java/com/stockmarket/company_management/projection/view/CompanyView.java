package com.stockmarket.company_management.projection.view;

import com.stockmarket.company_management.domain.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
public class CompanyView {
    private String companyName;
    private String companyCode;
    private String companyCEO;
    private Double companyTurnover;
    private String companyWebsite;
    private String stockExchange;
    private Instant createdDate;
    private String createdBy;
    private Instant modifiedDate;
    private String modifiedBy;

    public CompanyView(Company company) {
        this.companyName = company.getCompanyName();
        this.companyCode = company.getCompanyCode();
        this.companyCEO = company.getCompanyCEO();
        this.companyTurnover = company.getCompanyTurnover();
        this.companyWebsite = company.getCompanyWebsite();
        this.stockExchange = company.getStockExchange();
        this.createdBy = company.getCreatedBy();
        this.createdDate = company.getCreatedDate();
        this.modifiedBy = company.getModifiedBy();
        this.modifiedDate = company.getModifiedDate();
    }
}
