package com.example.dao;

import com.example.entity.Apartment;

import java.util.List;

public interface ApartmentDAO {
    void addApartment(Apartment apartment);
    void deleteApartment(Apartment apartment);
    void updateApartment(Apartment apartment);
    Apartment getApartmentById(int id);
    List<Apartment> getAllApartments();
    public void close();
    public List<Apartment> selectApartmentsByDistrict(String district);
    public List<Apartment> selectApartmentsByPriceRange(double minPrice, double maxPrice);
    public List<Apartment> getApartmentsByRooms(int rooms);
    public List<Apartment> getApartmentsByAreaRange(double minArea, double maxArea);
}
