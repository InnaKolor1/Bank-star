package com.project.Bank_star.Recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation001 {
    private UUID id;
    private String name;
    private String text;
}