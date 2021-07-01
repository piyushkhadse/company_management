package com.stockmarket.company_management.command;

import com.stockmarket.core.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCompany extends Command {

    private String companyName;
    private String companyCode;
    private String companyCEO;
    private Double companyTurnover;
    private String companyWebsite;
    private String stockExchange;

    public CreateCompany() {
        super(UUID.randomUUID().toString(), "COMPANY");
    }

    public CreateCompany(String companyName, String companyCode, String companyCEO, Double companyTurnover,
                         String companyWebsite, String stockExchange, String aggregateId) {
        super(aggregateId, "COMPANY");
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.companyCEO = companyCEO;
        this.companyTurnover = companyTurnover;
        this.companyWebsite = companyWebsite;
        this.stockExchange = stockExchange;
    }
}
