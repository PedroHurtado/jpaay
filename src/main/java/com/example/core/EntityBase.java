package com.example.core;

import java.util.Objects;
import java.util.UUID;

public class EntityBase{
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        //JAVA 16 pattern matching instance of
        //https://openjdk.org/jeps/394
        if(obj instanceof EntityBase e){
            return e.getId().equals(id);
        }
        return false;
    }
    private final UUID id;
    public EntityBase(UUID id){
        this.id = id;
    } 
    UUID getId(){
        return id;
    }
}
