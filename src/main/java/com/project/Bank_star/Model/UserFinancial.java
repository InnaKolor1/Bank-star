package com.project.Bank_star.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserFinancial {

    @Setter
    private Long debitProducts;
    @Setter
    private Long investProducts;
    @Setter
    private Double sumSavingDeposits;

    public UserFinancial(Long debitProducts, Long investProducts, Double sumSavingDeposits) {
        this.debitProducts = debitProducts;
        this.investProducts = investProducts;
        this.sumSavingDeposits = sumSavingDeposits;
    }

    public UserFinancial(Long debitProducts, Long investProducts, Double sumSavingDeposits, long cntCreditProducts, double sumDebitDeposits) {
        this.debitProducts = debitProducts;
        this.investProducts = investProducts;
        this.sumSavingDeposits = sumSavingDeposits;
        this.cntCreditProducts = cntCreditProducts;
        this.sumDebitDeposits = sumDebitDeposits;
    }

    @Setter
    private long cntCreditProducts;
    @Setter
    private double sumDebitDeposits;

}
