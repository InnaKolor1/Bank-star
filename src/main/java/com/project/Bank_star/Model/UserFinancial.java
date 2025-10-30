package com.project.Bank_star.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFinancial {
    private long DebitProducts;
    private long InvestProducts;
    private double sumSavingDeposits;

    public int setInvestProducts() {
    }
}