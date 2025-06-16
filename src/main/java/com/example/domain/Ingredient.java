package com.example.domain;

import java.util.UUID;

//Root
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
        //ingredient.new
        /*
         * la creacccion real del objeto(SI)
         * el mapeo de la bb.dd(No)
         */
        this.id = id;
        this.name = name;
        this.cost = cost;
    } 
    public void update(String name, double cost){
        //ingredient.update
        this.name = name;
        this.cost = cost;
    }      
    public static Ingredient create(UUID id, String name, double cost){
        //ingredient.new
        return new Ingredient(id, name, cost);
    }

}
