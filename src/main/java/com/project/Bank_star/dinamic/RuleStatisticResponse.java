package com.project.Bank_star.dinamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleStatisticResponse {
    private UUID ruleId;
    private Long count;
}
