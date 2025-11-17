package com.project.Bank_star.dinamic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleStatisticsListResponse {
    private List<RuleStatisticResponse> stats;
}


