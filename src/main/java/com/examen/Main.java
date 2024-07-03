package com.examen;

import java.util.Scanner;

import com.examen.modules.country.adapter.in.CountryConsoleAdapter;
import com.examen.modules.country.adapter.out.CountryMySQLRepository;
import com.examen.modules.country.application.CountryService;

import com.examen.modules.gender.adapter.in.GenderConsoleAdapter;
import com.examen.modules.gender.adapter.out.GenderMySQLRepository;
import com.examen.modules.gender.application.GenderService;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/movie";
        String username = "root";
        String password = "root";

        CountryMySQLRepository countryMySQLRepository = new CountryMySQLRepository(url, username, password);
        GenderMySQLRepository genderMySQLRepository = new GenderMySQLRepository(url, username, password);
        
        

        System.out.println("--------------- MENU PRINCIPAL ---------------");
        while (true) {
            System.out.println("1. country");
            System.out.println("2. gender");
            System.out.println("3. airportairline");
            System.out.println("");
            System.out.print("Ingrese la opcion: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("country");
                    CountryService countryService = new CountryService(countryMySQLRepository);
                    CountryConsoleAdapter countryConsoleAdapter = new CountryConsoleAdapter(countryService);
                    countryConsoleAdapter.start();
                    break;
                case 2:
                    System.out.println("gender");
                    GenderService genderService = new GenderService(genderMySQLRepository);
                    GenderConsoleAdapter genderConsoleAdapter = new GenderConsoleAdapter(genderService);
                    genderConsoleAdapter.start();
                    break;
                case 3:
                    
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");

        
                }
            }
    }
}
