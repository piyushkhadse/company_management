package com.stockmarket.company_management.domain;

import com.stockmarket.company_management.command.CreateCompany;
import com.stockmarket.core.domain.AggregateRoot;
import com.stockmarket.core.domain.Error;
import com.stockmarket.core.events.CompanyRegisteredEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document("companies")
@NoArgsConstructor
public class Company extends AggregateRoot {

    private String _id;
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

    public Company(CreateCompany createCompany) {
        validate(createCompany);
        if (createCompany.getErrors().isEmpty()) {
            this._id = createCompany.getAggregateId();
            this.companyCode = createCompany.getCompanyCode();
            this.companyName = createCompany.getCompanyName();
            this.companyCEO = createCompany.getCompanyCEO();
            this.companyWebsite = createCompany.getCompanyWebsite();
            this.companyTurnover = createCompany.getCompanyTurnover();
            this.stockExchange = createCompany.getStockExchange();
            this.createdDate = Instant.now();
            this.modifiedDate = Instant.now();
            CompanyRegisteredEvent event = new CompanyRegisteredEvent(this._id, createCompany.getAggregateType(),
                    this.companyName, this.companyCode, this.companyCEO, this.companyTurnover, this.companyWebsite,
                    this.stockExchange);
            registerEvent(event);
        }

    }

    private void validate(CreateCompany createCompany) {
        List<Error> errors = new ArrayList<>();
        if (mandatoryCheck(createCompany.getCompanyCode())) {
            errors.add(new Error("INVALID_INPUT", "companyCode is invalid input"));
            createCompany.setErrors(errors);
        } else if (mandatoryCheck(createCompany.getCompanyName())) {
            errors.add(new Error("INVALID_INPUT", "companyName is invalid input"));
            createCompany.setErrors(errors);
        } else if (mandatoryCheck(createCompany.getCompanyCEO())) {
            errors.add(new Error("INVALID_INPUT", "companyCEO is invalid input"));
            createCompany.setErrors(errors);
        } else if (createCompany.getCompanyTurnover() == null) {
            errors.add(new Error("INVALID_INPUT", "companyTurnover is invalid input"));
            createCompany.setErrors(errors);
        } else if (mandatoryCheck(createCompany.getCompanyWebsite())) {
            errors.add(new Error("INVALID_INPUT", "companyWebsite is invalid input"));
            createCompany.setErrors(errors);
        } else if (mandatoryCheck(createCompany.getStockExchange())) {
            errors.add(new Error("INVALID_INPUT", "stockExchange is invalid input"));
            createCompany.setErrors(errors);
        } else if (createCompany.getCompanyTurnover().compareTo(10.00d) < 0) {
            errors.add(new Error("INVALID_INPUT", "companyTurnover must be greater than 10Cr."));
            createCompany.setErrors(errors);
        }
    }

    private boolean mandatoryCheck(String field) {
        if (field == null || field.isEmpty()) {
            return true;
        }
        return false;
    }

}
