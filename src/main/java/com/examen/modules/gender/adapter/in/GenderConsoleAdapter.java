package com.examen.modules.gender.adapter.in;

import java.util.Optional;
import java.util.Scanner;

import com.examen.modules.gender.domain.Gender;
import com.examen.modules.country.application.CountryService;
import com.examen.modules.country.domain.Country;
import com.examen.modules.gender.application.GenderService;

public class GenderConsoleAdapter {
    private final GenderService genderService;

    public GenderConsoleAdapter(GenderService genderService) {
        this.genderService = genderService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el tipo de genero: ");
                    String createType = scanner.nextLine();

                    Gender gender = new Gender(createType);
                    genderService.saveGender(gender);
                    break;
                
                case 2:
                System.out.print("Ingrese  ID a actualizar: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el nuevo tipo de genero: ");
                String updateType = scanner.nextLine();

                Gender updatedGender = new Gender(updateId, updateType);
                genderService.updateGender(updatedGender);
                break;



                case 3:
                    System.out.print("Ingrese el Id del genero a buscar: ");
                    int findId = scanner.nextInt();
                    Optional<Gender> gender1 = genderService.findByIdGender(findId);
                        gender1.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getType()),
                        () -> System.out.println("genero no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del genero a borrar: ");
                    int deleteId = scanner.nextInt();
                    genderService.deleteGender(deleteId);
                    break;

                case 5:
                    genderService.findAllGender().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getType());
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
        System.out.println("1. Crear un genero");
        System.out.println("2. Actualizar un genero");
        System.out.println("3. Buscar un genero por ID");
        System.out.println("4. Eliminar un genero");
        System.out.println("5. Listar todos los generos");
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
