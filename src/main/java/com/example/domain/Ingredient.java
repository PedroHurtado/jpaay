package com.example.domain;

import java.util.UUID;

public class Ingredient {
    private UUID id;
    private String name;
    private double cost;
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
     protected Ingredient(UUID id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    } 
    public void update(String name, double cost){
        this.name = name;
        this.cost = cost;
    }      
    public static Ingredient create(UUID id, String name, double cost){
        return new Ingredient(id, name, cost);
    }

}
