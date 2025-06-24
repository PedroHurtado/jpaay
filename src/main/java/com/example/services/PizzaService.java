package com.example.services;


//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.domain.Ingredient;
import com.example.domain.Pizza;



public class PizzaService {
    
    private SessionFactory sessionFactory;
    
    public PizzaService() {
        try {
            // Configurar Hibernate
            Configuration configuration = new Configuration();
            //configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Ingredient.class);
            configuration.addAnnotatedClass(Pizza.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error inicializando SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // CRUD para Ingredient
    public void saveIngredient(Ingredient ingredient) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(ingredient);
            transaction.commit();
            System.out.println("Ingrediente guardado: " + ingredient.getName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error guardando ingrediente: " + e.getMessage());
        }
    }
    
    public Ingredient getIngredient(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ingredient.class, id);
        } catch (Exception e) {
            System.err.println("Error obteniendo ingrediente: " + e.getMessage());
            return null;
        }
    }
    
    public List<Ingredient> getAllIngredients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Ingredient", Ingredient.class).list();
        } catch (Exception e) {
            System.err.println("Error obteniendo ingredientes: " + e.getMessage());
            return List.of();
        }
    }
    
    public void updateIngredient(String id, String name, double cost) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ingredient ingredient = session.get(Ingredient.class, id);
            if (ingredient != null) {
                ingredient.update(name, cost);
                session.merge(ingredient);
                transaction.commit();
                System.out.println("Ingrediente actualizado: " + ingredient.getName());
            } else {
                System.out.println("Ingrediente no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error actualizando ingrediente: " + e.getMessage());
        }
    }
    
    public void deleteIngredient(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ingredient ingredient = session.get(Ingredient.class, id);
            if (ingredient != null) {
                session.remove(ingredient);
                transaction.commit();
                System.out.println("Ingrediente eliminado: " + ingredient.getName());
            } else {
                System.out.println("Ingrediente no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error eliminando ingrediente: " + e.getMessage());
        }
    }
    
    // CRUD para Pizza
    public void savePizza(Pizza pizza) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(pizza);
            transaction.commit();
            System.out.println("Pizza guardada: " + pizza.getName() + " - Precio: $" + pizza.getPrice());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error guardando pizza: " + e.getMessage());
        }
    }
    
    public Pizza getPizza(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Pizza.class, id);
        } catch (Exception e) {
            System.err.println("Error obteniendo pizza: " + e.getMessage());
            return null;
        }
    }
    
    public List<Pizza> getAllPizzas() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Pizza", Pizza.class).list();
        } catch (Exception e) {
            System.err.println("Error obteniendo pizzas: " + e.getMessage());
            return List.of();
        }
    }
    
    public void updatePizza(UUID id, String name, String description, String url) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Pizza pizza = session.get(Pizza.class, id);
            if (pizza != null) {
                pizza.update(name, description, url);
                session.merge(pizza);
                transaction.commit();
                System.out.println("Pizza actualizada: " + pizza.getName());
            } else {
                System.out.println("Pizza no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error actualizando pizza: " + e.getMessage());
        }
    }
    
    public void deletePizza(UUID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Pizza pizza = session.get(Pizza.class, id);
            if (pizza != null) {
                session.remove(pizza);
                transaction.commit();
                System.out.println("Pizza eliminada: " + pizza.getName());
            } else {
                System.out.println("Pizza no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error eliminando pizza: " + e.getMessage());
        }
    }
    
    public void addIngredientToPizza(String pizzaId, String ingredientId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Pizza pizza = session.get(Pizza.class, pizzaId);
            Ingredient ingredient = session.get(Ingredient.class, ingredientId);
            
            if (pizza != null && ingredient != null) {
                pizza.addIngedient(ingredient);
                session.merge(pizza);
                transaction.commit();
                System.out.println("Ingrediente " + ingredient.getName() + " agregado a pizza " + pizza.getName());
            } else {
                System.out.println("Pizza o ingrediente no encontrado");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error agregando ingrediente a pizza: " + e.getMessage());
        }
    }
    
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
