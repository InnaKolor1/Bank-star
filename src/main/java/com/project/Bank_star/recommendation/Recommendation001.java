package com.project.Bank_star.recommendation;

import java.util.UUID;

public class Recommendation001 {
    private UUID id;
    private String name;
    private String text;

    public Recommendation001() {}

    public Recommendation001(UUID id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setText(String text) { this.text = text; }
}