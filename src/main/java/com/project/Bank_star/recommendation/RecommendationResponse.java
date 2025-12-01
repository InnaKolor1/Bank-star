package com.project.Bank_star.recommendation;

import java.util.List;
import java.util.UUID;

public class RecommendationResponse {
    private UUID userId;
    private List<Recommendation001> recommendations;

    public RecommendationResponse() {}

    public RecommendationResponse(UUID userId, List<Recommendation001> recommendations) {
        this.userId = userId;
        this.recommendations = recommendations;
    }

    public UUID getUserId() { return userId; }
    public List<Recommendation001> getRecommendations() { return recommendations; }

    public void setUserId(UUID userId) { this.userId = userId; }
    public void setRecommendations(List<Recommendation001> recommendations) { this.recommendations = recommendations; }
}