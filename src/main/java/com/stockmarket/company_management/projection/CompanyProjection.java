package com.stockmarket.company_management.projection;

import com.stockmarket.company_management.domain.Company;
import com.stockmarket.company_management.projection.view.CompanyView;
import com.stockmarket.company_management.repository.CompanyRepository;
import com.stockmarket.core.domain.Error;
import com.stockmarket.core.exception.ApplicationException;
import com.stockmarket.core.logger.StockMarketApplicationLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyProjection {
    @Autowired
    private CompanyRepository repository;

    StockMarketApplicationLogger logger = StockMarketApplicationLogger.getLogger(this.getClass());

    /**
     * returns list of all companies
     *
     * @return
     */
    public List<CompanyView> getAllCompanyDetails() {
        try {
            List<Company> companies = repository.findAll();
            List<CompanyView> response = new ArrayList<>();
            if (!companies.isEmpty()) {
                companies.stream().flatMap(company -> {
                    response.add(new CompanyView(company));
                    return null;
                });
            }
            return response;
        } catch (Exception e) {
            logger.error().log("Exception while fetching all company details", e);
            throw new ApplicationException(new Error("INTERNAL_SERVER_ERROR", "Internal Server Error"), 500);
        }
    }

    /**
     * returns company details
     *
     * @param companyCode
     * @return
     */
    public CompanyView getCompanyDetails(String companyCode) {
        try {
            Company company = repository.findByCompanyCode(companyCode);
            if (company != null) {
                return new CompanyView(company);
            }
            throw new ApplicationException(new Error("NOT_FOUND", "Resource not found"), 404);
        } catch (ApplicationException exception) {
            logger.error().log("Exception while fetching company details for companyCode {}", companyCode, exception);
            throw exception;
        } catch (Exception e) {
            logger.error().log("Exception while fetching company details for companyCode {}", companyCode, e);
            throw new ApplicationException(new Error("INTERNAL_SERVER_ERROR", "Internal Server Error"), 500);
        }
    }
}
