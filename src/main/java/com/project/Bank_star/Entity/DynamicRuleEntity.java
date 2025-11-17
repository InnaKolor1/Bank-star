package com.project.Bank_star.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
    @Column(name = "id",nullable = false,updatable = false);


}
