package com.cognizant.loan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public Loan getLoanDetails(@PathVariable String number) {
        Loan loan = new Loan();
        loan.setNumber(number); 
        loan.setType("car");
        loan.setLoan(400000.00);
        loan.setEmi(3258.00);
        loan.setTenure(18);
        return loan;
    }
}