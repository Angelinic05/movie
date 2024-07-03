package com.examen.modules.country.adapter.in;

import java.util.Optional;
import java.util.Scanner;

import com.examen.modules.country.application.CountryService;
import com.examen.modules.country.domain.Country;

public class CountryConsoleAdapter {
    private final CountryService countryService;

    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del pais: ");
                    String createName = scanner.nextLine();

                    Country country = new Country(createName);
                    countryService.saveCountry(country);
                    break;
                
                case 2:
                System.out.print("Ingrese  ID a actualizar: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el nuevo nombre del pais: ");
                String updateName = scanner.nextLine();

                Country updatedCountry = new Country(updateId, updateName);
                countryService.updateCountry(updatedCountry);
                break;



                case 3:
                    System.out.print("Ingrese el Id del pais a buscar: ");
                    int findId = scanner.nextInt();
                    Optional<Country> country1 = countryService.findByIdCountry(findId);
                        country1.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName()),
                        () -> System.out.println("pais no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del pais a borrar: ");
                    int deleteId = scanner.nextInt();
                    countryService.deleteCountry(deleteId);
                    break;

                case 5:
                    countryService.findAllCountry().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName());
                    });
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    private int menu(Scanner scanner){
        System.out.println("1. Crear un pais");
        System.out.println("2. Actualizar un pais");
        System.out.println("3. Buscar un pais por ID");
        System.out.println("4. Eliminar un pais");
        System.out.println("5. Listar todos los paises");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese la opcion: ");
        int choice = -1;
        while (choice < 0 || choice > 5) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 6) {                    
                    System.out.println("Ingrese una opcion valida (1 - 5).");
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
        return choice;
    }
}
