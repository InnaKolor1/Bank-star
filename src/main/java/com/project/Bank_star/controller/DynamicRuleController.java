package com.project.Bank_star.controller;

import com.project.Bank_star.Model.DynamicRuleRequest;
import com.project.Bank_star.Model.DynamicRuleResponse;
import com.project.Bank_star.service.DynamicRuleService;
import com.project.Bank_star.dinamic.RuleStatisticsListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static ProjectAndRules.Invest500.log;

@RestController
@RequestMapping("/rule")
public class DynamicRuleController {
    private final DynamicRuleService dynamicRuleService;

    public DynamicRuleController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
    }

    @PostMapping
    public ResponseEntity<DynamicRuleResponse> createRule(@RequestBody DynamicRuleRequest request) {
        DynamicRuleResponse response = dynamicRuleService.createRule(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DynamicRuleResponse>> getAllRules() {
        List<DynamicRuleResponse> rules = dynamicRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteRule(@PathVariable UUID productId) {
        boolean deleted = dynamicRuleService.deleteRuleByProductId(productId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @GetMapping("/stats")
    public ResponseEntity<RuleStatisticsListResponse> getRuleStatistics() {
        log.debug("GET /rule/stats - Retrieving rule statistics");
        RuleStatisticsListResponse response = dynamicRuleService.getRuleStatistics();
        return ResponseEntity.ok(response);
    }
}