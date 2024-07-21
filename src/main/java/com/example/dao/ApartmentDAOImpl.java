package com.example.dao;

import com.example.entity.Apartment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.NoSuchElementException;

public class ApartmentDAOImpl implements ApartmentDAO{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public ApartmentDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("apartHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addApartment(Apartment apartment) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(apartment);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    //  the apartment is considered to have passed the availability check in the database
    @Override
    public void deleteApartment(Apartment apartment) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(apartment);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateApartment(Apartment apartment) {
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(apartment);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Apartment getApartmentById(int id) {
        return entityManager.find(Apartment.class, id);

    }

    @Override
    public List<Apartment> getAllApartments() {
       return entityManager.createQuery("SELECT a FROM Apartment a", Apartment.class).getResultList();
    }
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    @Override
    public List<Apartment> selectApartmentsByDistrict(String district){
        TypedQuery<Apartment>  query = entityManager.createQuery("SELECT a FROM Apartment a WHERE a.district = :district", Apartment.class);
        return query.setParameter("district", district).getResultList();
    }

    @Override
    public List<Apartment> selectApartmentsByPriceRange(double minPrice, double maxPrice) {
        return entityManager.createQuery("SELECT a FROM Apartment a WHERE a.price BETWEEN :minPrice AND :maxPrice", Apartment.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    @Override
    public List<Apartment> getApartmentsByRooms(int rooms) {
        return entityManager.createQuery("SELECT a FROM Apartment a WHERE a.rooms = :rooms")
                .setParameter("rooms", rooms)
                .getResultList();
    }

    @Override
    public List<Apartment> getApartmentsByAreaRange(double minArea, double maxArea) {
        return entityManager.createQuery("SELECt a FROM Apartment a WHERE a.area BETWEEN :min AND :max")
                .setParameter("min", minArea)
                .setParameter("max", maxArea)
                .getResultList();
    }
}
