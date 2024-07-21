package com.example.main;

import com.example.service.ApartmentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApartmentService apartmentService = new ApartmentService();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1: add apartment");
                System.out.println("2: delete apartment");
                System.out.println("3: change apartment");
                System.out.println("4: view apartments");
                System.out.println("5: fill apartments with random data");
                System.out.println("6: filter apartments by district");
                System.out.println("7: filter apartments by price range");
                System.out.println("8: filter apartments by number of rooms");
                System.out.println("9: filter apartments by area range");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        apartmentService.addApartment(sc);
                        break;
                    case "2":
                        apartmentService.deleteApartment(sc);
                        break;
                    case "3":
                        apartmentService.updateApartment(sc);
                        break;
                    case "4":
                        apartmentService.viewApartments();
                        break;
                    case "5":
                        apartmentService.fillApartments(sc);
                        break;
                    case "6":
                        apartmentService.selectApartmentsByDistrict(sc);
                        break;
                    case "7":
                        apartmentService.selectApartmentsByPriceRange(sc);
                        break;
                    case "8":
                        apartmentService.selectApartmentsByRooms(sc);
                        break;
                    case "9":
                        apartmentService.selectApartmentsByAreaRange(sc);
                        break;
                    default:
                        return;
                }
            }
        } finally {
            sc.close();
            apartmentService.close();
        }
    }
}
