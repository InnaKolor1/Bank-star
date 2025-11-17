package com.project.Bank_star.recommendation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class RecommendationResponse {
    private UUID userId;
    private List<Recommendation001> recommendations;

    public RecommendationResponse(UUID userId, List<Recommendation001> recommendations) {
        this.userId = userId;
        this.recommendations = recommendations;
    }

}

