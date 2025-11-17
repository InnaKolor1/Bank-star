package com.project.Bank_star.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@EntityScan
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DynamicRuleEntity {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,updatable = false)
    private UUID=id;

    @Column(name = "product_id",nullable = false,updatable = false)
    private UUID=productId;

    @Column(name = "name",nullable = false,updatable = false)
    private UUID=String productName;

    @Column(name = "text",nullable = false,updatable = false)
    private UUID=String productText;


}
