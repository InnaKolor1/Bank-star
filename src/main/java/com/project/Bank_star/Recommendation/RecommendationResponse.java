package com.project.Bank_star.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponse {
    private UUID user_id;
    private List<Recommendation001>recommendation001s;
}
