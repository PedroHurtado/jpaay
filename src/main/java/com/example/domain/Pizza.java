package com.example.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Pizza {
    private static final double PROFIT= 1.2D;
    private UUID id;
    private String name;
    private String description;
    private String url;
    private Set<Ingredient> ingredients;
    
    public Pizza(UUID id, String name, String description, String url, Set<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.ingredients = new HashSet<>(ingredients);
    }
    public double getPrice(){
        return ingredients.stream()
            .map(i->i.getCost())
            .reduce(0D, Double::sum) * PROFIT;
    }
    public Set<Ingredient> getIngredients() {
        return new HashSet<>(ingredients);
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public void addIngedient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }
    public void update(String name, String description, String url){
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public static Pizza create(UUID id, String name, String description, String url, Set<Ingredient> ingredients){
        return new Pizza(id, name, description, url, ingredients);
    }
    
    
}
