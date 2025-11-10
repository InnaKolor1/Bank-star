package com.project.Bank_star.Service;

import com.project.Bank_star.Entity.DynamicRuleEntity;
import com.project.Bank_star.Model.DynamicRuleRequest;
import com.project.Bank_star.Model.DynamicRuleResponse;
import com.project.Bank_star.Repository.DynamicRuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DynamicRuleService {
    private final DynamicRuleRepository dynamicRuleRepository;
    private final DynamicRuleConverter converter;

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository, DynamicRuleConverter converter) {
        this.dynamicRuleRepository = dynamicRuleRepository;
        this.converter = converter;
    }

    @Transactional
    public DynamicRuleResponse createRule(DynamicRuleRequest request) {
        DynamicRuleEntity entity = converter.toEntity(request);
        DynamicRuleEntity savedEntity = dynamicRuleRepository.save(entity);
        return converter.toResponse(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<DynamicRuleResponse> getAllRules() {
        return dynamicRuleRepository.findAll().stream()
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DynamicRuleEntity> getAllActiveRules() {
        return dynamicRuleRepository.findAll();
    }

    @Transactional
    public boolean deleteRuleByProductId(UUID productId) {
        if (dynamicRuleRepository.existsByProductId(productId)) {
            dynamicRuleRepository.deleteByProductId(productId);
            return true;
        }
        return false;
    }
}