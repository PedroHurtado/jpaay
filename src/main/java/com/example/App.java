package com.example;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.domain.Ingredient;

/**
 * Hello world!
 *
 */
public class App 
{
    //java 16
    public record Reqest(String name,double cost) {
    }
    public static void main( String[] args )
    {
        var request = new Reqest("Tomate", 1.5);
        var request1 = new Reqest("Tomate", 1.5);

        System.out.println(request.equals( request1)); //true
        System.out.println(request == request1); //false

        var id = UUID.randomUUID();
        var ingredient = Ingredient.create(id, "Tomate", 1.0);
        ingredient.update(request.name(), request.cost());

        var ingredient1 = Ingredient.create(UUID.randomUUID(), "TOMATE", 3);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient1);
        System.out.println(ingredients.size());

    }

    /*
     * EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();

        // Crear nueva persona
        em.getTransaction().begin();
        Persona persona = new Persona("Pedro Hurtado", 42);
        em.persist(persona);
        em.getTransaction().commit();

        // Leer persona
        Persona encontrada = em.find(Persona.class, persona.getId());
        System.out.println("Encontrada: " + encontrada);

        // Actualizar
        em.getTransaction().begin();
        encontrada.setEdad(43);
        em.merge(encontrada);
        em.getTransaction().commit();

        // Eliminar
        em.getTransaction().begin();
        em.remove(encontrada);
        em.getTransaction().commit();

        em.close();
        HibernateUtil.getSessionFactory().close();
     */
}

