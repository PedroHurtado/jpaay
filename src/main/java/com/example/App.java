package com.example;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.domain.Ingredient;
import com.example.domain.Pizza;
import com.example.services.PizzaService;

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
        runPizzaService();

    }
    private static void runPizzaService(){
        PizzaService pizzaService = new PizzaService();
        
        try {
            // Crear algunos ingredientes
            System.out.println("=== CREANDO INGREDIENTES ===");
            Ingredient queso = Ingredient.create(UUID.randomUUID(), "Queso Mozzarella", 2.50);
            Ingredient tomate = Ingredient.create(UUID.randomUUID(), "Salsa de Tomate", 1.20);
            Ingredient pepperoni = Ingredient.create(UUID.randomUUID(), "Pepperoni", 3.80);
            Ingredient champiñones = Ingredient.create(UUID.randomUUID(), "Champiñones", 2.00);
            
            // Guardar ingredientes
            pizzaService.saveIngredient(queso);
            pizzaService.saveIngredient(tomate);
            pizzaService.saveIngredient(pepperoni);
            pizzaService.saveIngredient(champiñones);
            
            // Listar ingredientes
            System.out.println("\n=== INGREDIENTES GUARDADOS ===");
            pizzaService.getAllIngredients().forEach(i -> 
                System.out.println("- " + i.getName() + ": $" + i.getCost())
            );
            
            // Crear pizzas
            System.out.println("\n=== CREANDO PIZZAS ===");
            Set<Ingredient> ingredientesMargherita = new HashSet<>();
            ingredientesMargherita.add(queso);
            ingredientesMargherita.add(tomate);
            
            Set<Ingredient> ingredientesPepperoni = new HashSet<>();
            ingredientesPepperoni.add(queso);
            ingredientesPepperoni.add(tomate);
            ingredientesPepperoni.add(pepperoni);
            
            Pizza margherita = Pizza.create(
                UUID.randomUUID(),
                "Pizza Margherita",
                "Clásica pizza italiana con queso mozzarella y salsa de tomate",
                "https://example.com/margherita.jpg",
                ingredientesMargherita
            );
            
            Pizza pizzaPepperoni = Pizza.create(
                UUID.randomUUID(),
                "Pizza Pepperoni",
                "Deliciosa pizza con pepperoni, queso mozzarella y salsa de tomate",
                "https://example.com/pepperoni.jpg",
                ingredientesPepperoni
            );
            
            // Guardar pizzas
            pizzaService.savePizza(margherita);
            pizzaService.savePizza(pizzaPepperoni);
            
            // Listar pizzas
            System.out.println("\n=== PIZZAS GUARDADAS ===");
            pizzaService.getAllPizzas().forEach(p -> {
                System.out.println("- " + p.getName() + ": $" + String.format("%.2f", p.getPrice()));
                System.out.println("  Descripción: " + p.getDescription());
                System.out.println("  Ingredientes: " + p.getIngredients().size());
            });
            
            // Actualizar una pizza
            System.out.println("\n=== ACTUALIZANDO PIZZA ===");
            pizzaService.updatePizza(
                margherita.getId(),
                "Pizza Margherita Premium",
                "Clásica pizza italiana con queso mozzarella premium y salsa de tomate casera",
                "https://example.com/margherita-premium.jpg"
            );  

            // Eliminar una pizza
            System.out.println("\n=== ELIMINANDO PIZZA ===");
            pizzaService.deletePizza(pizzaPepperoni.getId());
            
            
            
        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            pizzaService.close();
            System.out.println("\nRecursos cerrados correctamente.");
        }
    }


}

