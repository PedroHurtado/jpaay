package com.example.core;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class EntityBase{

    //Requerido por el ORM
    protected EntityBase(){
        this(null);
    }
    @Id
    private final UUID id;
    protected EntityBase(UUID id){
        this.id = id;
    } 
    public UUID getId(){
        return id;
    }

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
}
