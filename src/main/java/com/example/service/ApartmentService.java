package com.example.service;

import com.example.dao.ApartmentDAO;
import com.example.dao.ApartmentDAOImpl;
import com.example.entity.Apartment;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ApartmentService {
    private ApartmentDAO apartmentDAO;

    public ApartmentService() {
        apartmentDAO = new ApartmentDAOImpl();
    }
    public void addApartment(Scanner sc) {
        try {
            System.out.print("Enter district: ");
            String district = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();
            System.out.print("Enter area: ");
            double area = Double.parseDouble(sc.nextLine());
            System.out.print("Enter rooms: ");
            int rooms = Integer.parseInt(sc.nextLine());
            System.out.print("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());

            Apartment apartment = new Apartment();
            apartment.setDistrict(district);
            apartment.setAddress(address);
            apartment.setArea(area);
            apartment.setRooms(rooms);
            apartment.setPrice(price);

            apartmentDAO.addApartment(apartment);
        }
        catch (Exception e) {
            System.out.println("Failed to add apartment. Error: " + e.getMessage());
        }
    }

    public void deleteApartment(Scanner sc) {
        System.out.print("Enter apartment id: ");
        int id = Integer.parseInt(sc.nextLine());

        Apartment apartment = apartmentDAO.getApartmentById(id);
        if (apartment != null) {
            apartmentDAO.deleteApartment(apartment);
        } else {
            System.out.println("Apartment not found");
        }
    }
    public void updateApartment(Scanner sc) {
        try{
            System.out.print("Enter apartment id: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter new price: ");
            double price = Double.parseDouble(sc.nextLine());

            Apartment apartment = apartmentDAO.getApartmentById(id);
            if (apartment != null) {
                apartment.setPrice(price);
                apartmentDAO.updateApartment(apartment);
            }
            else {
                System.out.println("Apartment not found");
            }
        }
        catch (Exception e) {
            System.out.println("Failed to update apartment. Error: " + e.getMessage());
        }
    }

    public void viewApartments() {
        List<Apartment> apartments = apartmentDAO.getAllApartments();
        for (Apartment apartment : apartments) {
            System.out.println(apartment);
        }
    }
    public void selectApartmentsByDistrict(Scanner sc) {
        System.out.print("Enter district: ");
        String district = sc.nextLine();
        List<Apartment> apartments = apartmentDAO.selectApartmentsByDistrict(district);
        for (Apartment apartment : apartments) {
            System.out.println(apartment);
        }
    }
    public void fillApartments(Scanner sc) {
        try{
            System.out.println("Enter the number of apartments: ");
            int numberOfApartments = Integer.parseInt(sc.nextLine());
            String[] districts = {"Central", "North", "South", "East", "West"};
            String[] streets = {"Main St", "Oak St", "Pine St", "Maple St", "Cedar St"};
            Random rnd = new Random();

            for (int i = 0; i < numberOfApartments; i++) {
                String district = districts[rnd.nextInt(districts.length)];
                String address = (rnd.nextInt(900) + 100) + " " + streets[rnd.nextInt(streets.length)];
                double rawArea = 50 + rnd.nextDouble() * 150;
                double price = 50000 + rnd.nextInt(450000);
                double area = 50 + rnd.nextInt(150);
                // Округление значения площади до двух знаков после запятой

                Apartment apartment = new Apartment();
                apartment.setDistrict(district);
                apartment.setAddress(address);
                apartment.setArea(area);
                apartment.setRooms(rnd.nextInt(6) + 1);
                apartment.setPrice(price);

                apartmentDAO.addApartment(apartment);
            }
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
    }
    public void selectApartmentsByPriceRange(Scanner sc){
        try{
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(sc.nextLine());
            System.out.print("Enter maximum price: ");
            double maxPrice = Double.parseDouble(sc.nextLine());

            List<Apartment> apartments = apartmentDAO.selectApartmentsByPriceRange(minPrice, maxPrice);
            for (Apartment apartment : apartments) {
                System.out.println(apartment);
            }
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
    }
    public void selectApartmentsByRooms(Scanner sc){
        try{
            System.out.print("Enter number of rooms: ");
            int rooms = Integer.parseInt(sc.nextLine());
            List<Apartment> apartments = apartmentDAO.getApartmentsByRooms(rooms);
            for (Apartment apartment : apartments) {
                System.out.println(apartment);
            }
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
    }
    public void selectApartmentsByAreaRange(Scanner sc){
        try{
            System.out.print("Enter minimum area: ");
            double minArea = Double.parseDouble(sc.nextLine());
            System.out.print("Enter maximum area: ");
            double maxArea = Double.parseDouble(sc.nextLine());

            List<Apartment> apartments = apartmentDAO.getApartmentsByAreaRange(minArea, maxArea);
            for (Apartment apartment : apartments) {
                System.out.println(apartment);
            }
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
    }
    public void close(){
        apartmentDAO.close();
    }
}
