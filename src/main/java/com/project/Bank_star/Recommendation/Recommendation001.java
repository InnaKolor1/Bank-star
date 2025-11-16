package com.project.Bank_star.Recommendation;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Recommendation001 {
    private UUID id;
    private String name;
    private String text;


    public Recommendation001(UUID id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

}
