package com.project.Bank_star.Entity;

import jakarta.persistence.*;



import java.util.UUID;

@Entity
@Table(name = "dynamic_rules")


public class DynamicRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "product_id", nullable = false, updatable = false)
    private UUID productId;

    @Column(name = "name", nullable = false)
    private String productName;

    @Column(name = "text", nullable = false)
    private String productText;

    public DynamicRuleEntity() {}


    public DynamicRuleEntity(UUID id, UUID productId, String productName, String productText) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productText = productText;
    }

    public UUID getId() { return id; }
    public UUID getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductText() { return productText; }

    public void setId(UUID id) { this.id = id; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductText(String productText) { this.productText = productText; }
}