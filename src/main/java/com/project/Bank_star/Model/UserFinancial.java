package com.project.Bank_star.Model;

public class UserFinancial {
    private Long debitProducts;
    private Long investProducts;
    private Double sumSavingDeposits;

    public UserFinancial() {}

    public UserFinancial(Long debitProducts, Long investProducts, Double sumSavingDeposits) {
        this.debitProducts = debitProducts;
        this.investProducts = investProducts;
        this.sumSavingDeposits = sumSavingDeposits;
    }

    public Long getDebitProducts() { return debitProducts; }
    public Long getInvestProducts() { return investProducts; }
    public Double getSumSavingDeposits() { return sumSavingDeposits; }

    public void setDebitProducts(Long debitProducts) { this.debitProducts = debitProducts; }
    public void setInvestProducts(Long investProducts) { this.investProducts = investProducts; }
    public void setSumSavingDeposits(Double sumSavingDeposits) { this.sumSavingDeposits = sumSavingDeposits; }
}