package edu.mum.cs544;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App {

	private static EntityManagerFactory emf;
    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Create new instance of Car and set values in it
        Owner owner = new Owner(null, "David", "1000N 4TH AVE");
        // Create new instance of Car and set values in it
        Car car1 = new Car(null, "BMW", "SDA231", 30221.00, owner);
        Car car2 = new Car(null, "Mercedes", "HOO100", 4088.00, owner);
        // save the cars
        em.persist(car1);
        em.persist(car2);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        // retieve all cars
        TypedQuery<Car> query = em.createQuery("from Car", Car.class);
        List<Car> carList = query.getResultList();
        for (Car car : carList) System.out.println(car);
        em.getTransaction().commit();
        em.close();
    }
}

